import Dexie, {Table} from "dexie"
import { City } from "../model"

//Step 1: //creaing a database class AppDB which extends dexie
//see model.ts for City creation
export class AppDB extends Dexie{
    cities !: Table<City, string> //data type is City, string is the primary key's type

    //define the schema, database is  named 'AppDB' and 'cities' is schema table name, version 1
    //standard code
    constructor(){
        super('AppDB')
        this.version(1).stores({
            cities: 'code, city_name'
        })
        // this.version(2).stores({
        //     cities: '++id, code, city_name'
        // });
        // this.version(3).stores({
        // cities: '[code+city_name], code, city_name'
        // });
    }   

    //add data to table method, or update if key already exists
    async addCity(item: City){
        const cityListId = await this.cities.put(item)
        console.log(`City added with id: ${cityListId}`) //prints primary key, code in version1, id in version 2
    }


    // async getallCitiesVoid(): Promise<void>{
    //     const allCities: City[] = await this.cities.toArray()
    //     console.info('get all cities: ', allCities)
    // }

    //get all
    async getallCities(): Promise<City[]>{
        const allCities: City[] = await this.cities.toArray()
        // console.info('get all cities: ', allCities)
        return allCities
    }

    //get with pagination
    async getWithPagi(): Promise<City[]>{
        const getPagi: City[] = await this.cities.offset(3).limit(3).toArray()
        // console.info('get with pagi: ', getPagi)
        return getPagi
    }

    //get by key
    async getWithKey(): Promise<City | undefined>{
        const getKey: City | undefined = await this.cities.get('SG') //primary key
        // console.info('get with key SG: ', getKey)
        return getKey
    }

    // Processing one by one
        // this.cart.orderBy('date').reverse().each(c => { ... })

    // Query with filters
        // this.cart.filter(c => c.date > yesterday).toArray()
        // this.cart.where('username').equals('fred').toArray()




}


//export new instance of AppData to be used publicly
export const db = new AppDB()







