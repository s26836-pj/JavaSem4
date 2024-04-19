package org.example.zad1_zjazd4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @GetMapping
    public List<FlightResponse> listFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return flights.stream()
                .map(flightMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FlightResponse createFlight(@RequestBody FlightRequest flightRequest){
        Flight flight = new Flight();
        Flight savedFlight = flightService.saveFlight(flight);
        return FlightResponse.fromFlight(savedFlight);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable UUID id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight(@PathVariable UUID id, @RequestBody FlightRequest flightRequest) {
        Flight existingFlight = flightService.getFlightById(id);

        if (existingFlight == null) {
            return ResponseEntity.notFound().build();
        }
        Flight updatedFlight = flightService.saveFlight(existingFlight);

        return ResponseEntity.ok(FlightResponse.fromFlight(updatedFlight));
    }

}