import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UploadComponent } from './components/upload.component';
import { ViewImageComponent } from './components/view-image.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient } from '@angular/common/http';
import { FileUpload } from './services/fileupload.service';
import { RouterModule, Routes } from '@angular/router';
import { CitiesService } from './services/cities.service';

const appRoutes: Routes = [
  {path:'', component: UploadComponent},
  {path:'image/:postId', component:ViewImageComponent},
  {path:'**', redirectTo: '/', pathMatch:'full'}
] 

@NgModule({
  declarations: [
    AppComponent,
    UploadComponent,
    ViewImageComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,  RouterModule.forRoot(appRoutes)
  ],
  providers: [provideHttpClient(), FileUpload, CitiesService, provideAnimationsAsync()], //new CitiesService********
  bootstrap: [AppComponent]
})
export class AppModule { }
