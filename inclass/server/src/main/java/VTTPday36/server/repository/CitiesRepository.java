package VTTPday36.server.repository;

// import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import VTTPday36.server.models.City;

@Repository
public class CitiesRepository {
    private static final String SELECT_ALL_CITIES = "SELECT code, city_name from cities";

    @Autowired
    private JdbcTemplate template;

    //query database, get multiple records, iterate, extract and create city object and accumulated into list
    public List<City> getallCities(){
        return template.query(SELECT_ALL_CITIES,
        (rs, rowNum) ->{
            System.out.println(rowNum); //debugging how many rows are returned
            return City.populate(rs);
        });
    }







}
