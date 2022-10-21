package edu.msudenver.event;


import edu.msudenver.city.City;
import edu.msudenver.country.Country;
import edu.msudenver.venue.Venue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventRepository eventRepository;

    @SpyBean
    private EventService eventService;


    @Test
    public void testGetEvents() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/events/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        Country testCountry = new Country("United States", "us");
        City testCity = new City("Denver", "80203", testCountry);
        Venue testVenue = new Venue("Mission Ballroom", "1234 test avenue", "public", testCity, true);
        Event testEvent = new Event();
        testEvent.setTitle("wedding");
        testEvent.setVenueId(testVenue);

        Mockito.when(eventRepository.findAll()).thenReturn(Arrays.asList(testEvent));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("wedding"));
    }

    @Test
    public void testGetEvent() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/events/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        Country testCountry = new Country("United States", "us");
        City testCity = new City("Denver", "80203", testCountry);
        Venue testVenue = new Venue("Mission Ballroom", "1234 test avenue", "public", testCity, true);
        Event testEvent = new Event();
        testEvent.setTitle("wedding");
        testEvent.setVenueId(testVenue);

        Mockito.when(eventRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testEvent));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("wedding"));
    }




    @Test
    public void testGetEventNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/events/11")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(eventRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }



    @Test
    public void testCreateCountry() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/events/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"wedding\", \"eventId\":\"1\"} ")
                .contentType(MediaType.APPLICATION_JSON);

        Country testCountry = new Country("United States", "us");
        City testCity = new City("Denver", "80203", testCountry);
        Venue testVenue = new Venue("Mission Ballroom", "1234 test avenue", "public", testCity, true);
        Event testEvent = new Event();
        testEvent.setTitle("wedding");
        testEvent.setVenueId(testVenue);
        Mockito.when(eventRepository.save(Mockito.any())).thenReturn(testEvent);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("wedding"));
    }

    @Test
    public void testCreateEventBadRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/events/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"wedding\", \"eventId\":\"1\"}")
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(eventRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }

    @Test
    public void testUpdateEvent() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/events/1")
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"eventId\":\"1\", \"title\":\"wedding22\"}")
                .contentType(MediaType.APPLICATION_JSON);

        Country testCountry = new Country("United States", "us");
        City testCity = new City("Denver", "80203", testCountry);
        Venue testVenue = new Venue("Mission Ballroom", "1234 test avenue", "public", testCity, true);
        Event testEvent = new Event();
        testEvent.setTitle("wedding");
        testEvent.setVenueId(testVenue);
        Mockito.when(eventRepository.findById(Mockito.any())).thenReturn(Optional.of(testEvent));

        Event testEventUpdated = new Event();
        testEventUpdated.setTitle("wedding22");
        testEventUpdated.setVenueId(testVenue);
        Mockito.when(eventRepository.save(Mockito.any())).thenReturn(testEventUpdated);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("wedding22"));
    }


    @Test
    public void testUpdateEventNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/events/1")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"eventId\":\"1\"}")
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(eventRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }


    @Test
    public void testUpdateEventBadRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/events/1")
                .accept(MediaType.APPLICATION_JSON)
                .content("{ \"eventId\":\"1\", \"title\":\"wedding\"}")
                .contentType(MediaType.APPLICATION_JSON);

        Country testCountry = new Country("United States", "us");
        City testCity = new City("Denver", "80203", testCountry);
        Venue testVenue = new Venue("Mission Ballroom", "1234 test avenue", "public", testCity, true);
        Event testEvent = new Event();
        testEvent.setTitle("wedding");
        testEvent.setVenueId(testVenue);
        Mockito.when(eventRepository.findById(Mockito.any())).thenReturn(Optional.of(testEvent));

        Mockito.when(eventRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }

    @Test
    public void testDeleteEvents() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/events/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        Country testCountry = new Country("United States", "us");
        City testCity = new City("Denver", "80203", testCountry);
        Venue testVenue = new Venue("Mission Ballroom", "1234 test avenue", "public", testCity, true);
        Event testEvent = new Event();
        testEvent.setTitle("wedding");
        testEvent.setVenueId(testVenue);
        Mockito.when(eventRepository.findById(Mockito.any())).thenReturn(Optional.of(testEvent));
        Mockito.when(eventRepository.existsById(Mockito.any())).thenReturn(true);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }




    @Test
    public void testDeleteEventNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/events/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(eventRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(eventRepository.existsById(Mockito.any())).thenReturn(false);
        Mockito.doThrow(IllegalArgumentException.class)
                .when(eventRepository)
                .deleteById(Mockito.any());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }
}


