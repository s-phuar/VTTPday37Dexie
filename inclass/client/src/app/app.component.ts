import { AfterViewInit, Component, OnInit } from '@angular/core';
import { CitiesService } from './services/cities.service';
import { db } from './shared/app.db';


//Step 3: AppComponent runs city grabbing method on init
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{

  constructor(private citiesService: CitiesService){
    console.info('AppComponent Initialized.....')
    this.citiesService.initCitiesToIndexDb()
  } 

  ngOnInit(): void {

  }



}
