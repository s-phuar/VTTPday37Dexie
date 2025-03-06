import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { UploadResult } from "../model";

@Injectable()

export class FileUpload{
    private http = inject(HttpClient)

    // upload image to sql
    //returns a promise
    upload(form:any, image:Blob){
        const formData = new FormData()
        formData.set('comments', form['comments'])
        formData.set('file', image)

        return lastValueFrom(this.http.post<UploadResult>('/api/upload', formData))

    }

    // get image from sql, almost immediately after upload
    getImage(postId: string){
        return lastValueFrom(this.http.get<UploadResult>(`/api/get-image/${postId}`))
    }
    



}