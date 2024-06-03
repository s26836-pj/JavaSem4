package org.example.zad1_zjazd4;


import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.FlightRequest;
import io.swagger.client.model.FlightResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flights")
public class FlightController extends DefaultApi {
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @GetMapping
    public List<FlightResponse> listFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return flights.stream()
                .map(flightMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FlightResponse createFlight(@RequestBody FlightRequest flightRequest) {
        Flight flight = flightMapper.mapToEntity(flightRequest);
        Flight savedFlight = flightService.saveFlight(flight);
        return flightMapper.mapEntityToResponse(savedFlight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable UUID id) {
        flightService.deleteFlight(id);
    }

    @PutMapping("/{id}")
    public FlightResponse updateFlight(@PathVariable UUID id, @RequestBody FlightRequest flightRequest) {
        Flight existingFlight = flightService.getFlightEntityById(id); // Użyj metody zwracającej Flight
        if (existingFlight == null) {
            throw new FlightNotFoundException("Flight not found with id: " + id);
        }
        Flight updatedFlight = flightMapper.update(flightRequest, existingFlight);
        Flight savedFlight = flightService.saveFlight(updatedFlight);
        return flightMapper.mapEntityToResponse(savedFlight);
    }

    @GetMapping("/{id}")
    public FlightResponse getFlightById(@PathVariable UUID id) {
        Flight flight = flightService.getFlightEntityById(id); // Użyj metody zwracającej Flight
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found with id: " + id);
        }
        return flightMapper.mapEntityToResponse(flight);
    }
}
