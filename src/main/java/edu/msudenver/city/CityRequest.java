package edu.msudenver.city;

public class CityRequest {
    private String name;
    private String countryCode;
    private String postalCode;

    public CityRequest(String name, String countryCode, String postalCode) {
        this.name = name;
        this.countryCode = countryCode;
        this.postalCode = postalCode;
    }

    public CityRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
