import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { City } from "../model";
import { db } from "../shared/app.db";


//Step 2: use shared db.ts to retrieve Cities from Spring
@Injectable()

export class CitiesService{

    cities: City[] = []

    private http = inject(HttpClient)

    getCities(){
        return lastValueFrom(this.http.get<City[]>('/api/cities')) //automapping feature as long as we match exactly
    }


    async initCitiesToIndexDb(){
        await db.cities.clear() //clear cities schema in indexDB
        this.cities = await this.getCities() //grab cities from spring
        console.info('retrieved cities: ', this.cities)
        this.cities.forEach(async city => { //add cities from spring into indexDB
            console.info('adding1...', city)
            console.info('adding2...', city.code)
            console.info('adding3...', city.city_name)
            await db.addCity({
                code: city.code,
                city_name: city.city_name
                
            })
        })
    }


    



}