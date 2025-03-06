package VTTPday36.server.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
    @Autowired
    private AmazonS3 s3Client;

    @Value("${do.storage.bucket}")
    private String bucketName;

    @Value("${do.storage.endpoint}")
    private String endPoint;

    //upload to s3 bucket
    public String upload(MultipartFile file, String comments, String postId) throws IOException{
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("comments", comments);
        userData.put("postId", postId);
        userData.put("fileName", file.getOriginalFilename());
        userData.put("fileName", String.valueOf(file.getSize()));
        userData.put("uploadDateTime", LocalDateTime.now().toString() );

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setUserMetadata(userData);

        //extract file type
        StringTokenizer tk = new StringTokenizer(file.getOriginalFilename(), ".");
        int count  = 0;
        String fileNameExt = "";
        while (tk.hasMoreTokens()) {
            if(count == 0){
                fileNameExt = tk.nextToken();
            }else{
                fileNameExt = tk.nextToken() + "." + fileNameExt;
            }
            count ++;
        }
        System.out.println(fileNameExt);
        if(fileNameExt.equals("blob")){
            fileNameExt=fileNameExt + ".png";
        }

        //instantiate put object request
        PutObjectRequest req = new PutObjectRequest(bucketName, "fileupload%s.%s".formatted(postId, fileNameExt), file.getInputStream(), metadata);
        req.withCannedAcl(CannedAccessControlList.PublicRead);
        s3Client.putObject(req);

        //constructing the url via hardcode if we want to use the url on angular
        return "https://%s.%s/fileupload%s.%s".formatted(bucketName, endPoint, postId, fileNameExt);

        //older working version
            // return "picture%s.%s".formatted(postId,fileNameExt);
    }





}
