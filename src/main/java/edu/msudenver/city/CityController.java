package edu.msudenver.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(cityService.getCities());
    }

    @GetMapping(path = "/{countryCode}/{postalCode}", produces = "application/json")
    public ResponseEntity<City> getCity(@PathVariable String countryCode, @PathVariable String postalCode) {
        City city = cityService.getCity(postalCode, countryCode);
        return new ResponseEntity<>(city, city == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    // Example Payload:
    /*
    {
       "countryCode": "us",
       "postalCode": "80232",
       "name": "Lakewood"
    }
     */
//    public ResponseEntity<City> createCity(@RequestBody CityRequest cityRequest) {
    // Example payload:
    /*
    {
        "country": {
            "countryCode": "us"
        },
        "postalCode": "80232",
        "name": "Lakewood"
    }
     */
    public ResponseEntity<City> createCity(@RequestBody CityRequest cityRequest) {
        try {
            return new ResponseEntity<>(cityService.saveCity(new City(cityRequest)), HttpStatus.CREATED);
            //return new ResponseEntity<>(cityService.saveCity(city), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{countryCode}/{postalCode}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<City> updateCity(@PathVariable String countryCode,
                                           @PathVariable String postalCode,
                                           @RequestBody City updatedCity) {
        City retrievedCity = cityService.getCity(postalCode, countryCode);
        if (retrievedCity != null) {
            retrievedCity.setName(updatedCity.getName());
            try {
                return ResponseEntity.ok(cityService.saveCity(retrievedCity));
            } catch(Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{countryCode}/{postalCode}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String countryCode,
                                              @PathVariable String postalCode) {
        return new ResponseEntity<>(cityService.deleteCity(postalCode, countryCode) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}
