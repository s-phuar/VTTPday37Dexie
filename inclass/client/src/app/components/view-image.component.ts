import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FileUpload } from '../services/fileupload.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-view-image',
  standalone: false,
  templateUrl: './view-image.component.html',
  styleUrl: './view-image.component.css'
})
export class ViewImageComponent implements OnInit, OnDestroy{

  postId = ''
  param$ !:Subscription
  imageData: any

  //activatedroute for path variable, grabs url data
  private activatedRoute = inject(ActivatedRoute)
  private fileUploadSvc = inject(FileUpload)

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
        async(params) => {
        this.postId = params['postId'] //grab uuid from url
        let result = await this.fileUploadSvc.getImage(this.postId) //wait for data to be fetched before proceeding
        console.log(result)
        this.imageData = result.image //result.image is returned from spring controller almost immediately after upload
    })
  }


  ngOnDestroy(): void {
    this.param$.unsubscribe()
  }



}
