package VTTPday36.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VTTPday36.server.models.City;
import VTTPday36.server.repository.CitiesRepository;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;

    public Optional<List<City>> getAllCities(){
        List<City> cities = this.citiesRepository.getallCities();
        if(cities != null){
            return Optional.of(cities);
        }
        return Optional.empty();
    }



}
