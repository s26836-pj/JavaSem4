package org.example.zad1_zjazd4;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class FlightResponse {
    private UUID id;
    private String name;
    private String destination;


    public static FlightResponse fromFlight(Flight flight) {
        FlightResponse response = new FlightResponse();
        response.setId(flight.getId());
        response.setName(flight.getName());
        response.setDestination(flight.getDestination());
        return response;
    }
}