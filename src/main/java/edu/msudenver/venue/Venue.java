package edu.msudenver.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.city.City;

import javax.persistence.*;

@Entity
@Table(name = "venues")
public class Venue {

    String Name;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long venueId;
    String streetAddress;
    String type;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "country_code"),
        @JoinColumn(name = "postal_code")
    })
    private City city;
    Boolean active;

    public Venue() {
    }

    public Venue(String name, String streetAddress, String type, City city, Boolean active) {
        this.Name = name;
        this.streetAddress = streetAddress;
        this.type = type;
        this.city = city;
        this.active = active;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Long getVenueId() { return venueId; }

    public void setVenueId(Long venueId) { this.venueId = venueId;  }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) { this.active = active; }
}
