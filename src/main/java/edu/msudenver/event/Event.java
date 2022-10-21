package edu.msudenver.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.venue.Venue;
import org.hibernate.type.TimestampType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "events")
public class Event {

    String title;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long eventId;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date starts;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date ends;
    @ManyToOne
    @JoinColumn(name = "venue_id")
    //@JsonProperty("venue")
    Venue venueId;
    //String colors;


    public Event() {
    }

    public Event(String title, Timestamp starts, Timestamp ends, Venue venueId) {
        this.title = title;
        this.starts = starts;
        this.ends = ends;
        this.venueId = venueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Venue getVenueId() {
        return venueId;
    }

    public void setVenueId(Venue venueId) {
        this.venueId = venueId;
    }

    public java.util.Date getStart() {
        return starts;
    }

    public void setStart(java.util.Date starts) {
        this.starts = starts;
    }

    public java.util.Date getEnd() {
        return ends;
    }

    public void setEnd(java.util.Date ends) {
        this.ends = ends;
    }

    /*public String getColors() {
        return this.colors;
    }

    public void setColors(String colors){
        this.colors = colors;
    }*/
}
