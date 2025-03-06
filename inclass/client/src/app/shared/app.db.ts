import Dexie, {Table} from "dexie"
import { City } from "../model"


export class AppDB extends Dexie{
    cities !: Table<City, string>

    constructor(){
        super('AppDB')
        this.version(1).stores({
            cities: 'code, city_name'
        })
    }

    async addCity(item: City){
        const cityListId = await this.cities.put(item)
        console.log(`City added with id: ${cityListId}`)
    }
}



export const db = new AppDB()







