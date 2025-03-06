import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { City } from "../model";
import { db } from "../shared/app.db";

@Injectable()

export class CitiesService{

    cities: City[] = []

    private http = inject(HttpClient)

    getCities(){
        return lastValueFrom(this.http.get<City[]>('/api/cities')) //automapping feature as long as we match exactly
    }


    async initCitiesToIndexDb(){
        await db.cities.clear()
        this.cities = await this.getCities()
        this.cities.forEach(async city => {
            await db.addCity({
                code: city.code,
                city_name: city.city_name
            })
        })
    }


    



}