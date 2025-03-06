package VTTPday36.server.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import VTTPday36.server.models.Post;
import VTTPday36.server.service.FileUploadService;
import VTTPday36.server.service.S3Service;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
public class FileUploadController {

    private static final String BASE64_PREFIX = "data:image/png;base64,";

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private S3Service s3Service;

    @PostMapping(path="/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> upload(
        @RequestPart("file") MultipartFile file,
        @RequestPart("comments") String comments){
        
            String postId = "";
            String outputUrl = "";
            try{
                postId = this.fileUploadService.upload(file, comments);
                System.out.println(postId);
                if(postId !=null && !postId.isEmpty()){ //S3
                    outputUrl = this.s3Service.upload(file, comments, postId); //S3
                    System.out.println(outputUrl);
                }
            }catch(SQLException | IOException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }

            JsonObject obj = Json.createObjectBuilder()
                .add("postId", postId)
                .build();
            
            return ResponseEntity.ok(obj.toString());
    }

    @GetMapping(path = "/api/get-image/{postId}")
    public ResponseEntity<String> getImage(@PathVariable(name="postId") String postId){
        Optional<Post> optionalP = this.fileUploadService.getPostById(postId);
        Post p = optionalP.get();

        String encodingString = Base64.getEncoder().encodeToString(p.getImage());

        JsonObject obj = Json.createObjectBuilder()
            .add("image", BASE64_PREFIX + encodingString)
            .build();

        return ResponseEntity.ok(obj.toString());
    }






}
