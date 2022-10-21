package edu.msudenver.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public City getCity(String postalCode, String countryCode) {
        CityId id = new CityId(postalCode, countryCode);
        try {
            return cityRepository.findById(id).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            return null;
        }
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public boolean deleteCity(String postalCode, String countryCode) {
        CityId id = new CityId(postalCode, countryCode);
        try {
            if(cityRepository.existsById(id)) {
                cityRepository.deleteById(id);
                return true;
            }
        } catch(IllegalArgumentException e) {}

        return false;
    }
}
