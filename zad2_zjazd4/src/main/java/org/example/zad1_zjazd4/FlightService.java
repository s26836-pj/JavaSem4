package org.example.zad1_zjazd4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper mapper;
    @Autowired
    public FlightService(FlightRepository flightRepository, FlightMapper mapper){
        this.flightRepository = flightRepository;
        this.mapper = mapper;
    }
    public List<Flight> getAllFlights(){
        return  flightRepository
                .findAll();

    }
    public Flight saveFlight(Flight flight){
        return flightRepository.save(flight);
    }


    public void deleteFlight(UUID id) {
        flightRepository.deleteById(id);
    }

    public Flight getFlightById(UUID id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        return flightOptional.orElse(null);
    }
}
