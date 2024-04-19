package org.example.zad1_zjazd4;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-07T21:48:38+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public Passenger toEntity(FlightRequest request) {
        if ( request == null ) {
            return null;
        }

        Passenger passenger = new Passenger();

        if ( request.getName() != null ) {
            passenger.setName( request.getName() );
        }

        return passenger;
    }

    @Override
    public FlightResponse toResponse(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        FlightResponse flightResponse = new FlightResponse();

        if ( flight.getId() != null ) {
            flightResponse.setId( flight.getId() );
        }
        if ( flight.getName() != null ) {
            flightResponse.setName( flight.getName() );
        }
        if ( flight.getDestination() != null ) {
            flightResponse.setDestination( flight.getDestination() );
        }

        return flightResponse;
    }
}
