package org.example.zad1_zjazd4;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.FlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlightService extends DefaultApi {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(UUID id) {
        flightRepository.deleteById(id);
    }

    public Flight getFlightEntityById(UUID id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        return flightOptional.orElse(null);
    }

    public FlightResponse getFlightById(UUID id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        return flightOptional.map(flightMapper::mapEntityToResponse).orElse(null);
    }
}
