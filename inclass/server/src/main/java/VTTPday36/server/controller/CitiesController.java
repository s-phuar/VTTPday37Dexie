package VTTPday36.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import VTTPday36.server.models.City;
import VTTPday36.server.service.CitiesService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@Controller
public class CitiesController {
    @Autowired
    private CitiesService citiesService;


    @GetMapping(path = "/api/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCities(){
        JsonArray result = null;

        Optional<List<City>> cities = citiesService.getAllCities();
        List<City> cityList = cities.get();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(City c: cityList){
            arrayBuilder.add(c.toJson());
        }

        result = arrayBuilder.build();

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());
    }





}
