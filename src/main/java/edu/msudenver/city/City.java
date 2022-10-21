package edu.msudenver.city;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.country.Country;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@IdClass(CityId.class)
public class City {
    private String name;

    @Id
    private String postalCode;

    @Id
    @ManyToOne
    @JoinColumn(name = "country_code")
    @JsonProperty("country")
    private Country countryCode;

    public City(String name, String postalCode, Country countryCode) {
        this.name = name;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public City(CityRequest cityRequest) {
        this.name = cityRequest.getName();
        this.postalCode = cityRequest.getPostalCode();
        this.countryCode = new Country().setCountryCode(cityRequest.getCountryCode());
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
    }
}
