package edu.msudenver.country;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {
    String countryName;
    @Id
    String countryCode;

    public Country() {

    }

    public Country(String countryName, String countryCode){
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public Country(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public Country setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Country setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }
}
