package edu.msudenver.city;

import java.io.Serializable;

public class CityId implements Serializable {
    private String postalCode;
    private String countryCode;

    public CityId(String postalCode, String countryCode) {
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public CityId() {
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
