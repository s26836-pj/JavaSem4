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

    @Autowired
    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightResponse> listFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return flights.stream()
                .map(FlightResponse::fromFlight)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FlightResponse createFlight(@RequestBody FlightRequest flightRequest){
        Flight flight = new Flight();
        flight.setName(flightRequest.getName());
        flight.setDestination(flightRequest.getDestination());
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

        existingFlight.setName(flightRequest.getName());
        existingFlight.setDestination(flightRequest.getDestination());

        Flight updatedFlight = flightService.saveFlight(existingFlight);

        return ResponseEntity.ok(FlightResponse.fromFlight(updatedFlight));
    }

}