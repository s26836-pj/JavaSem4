package org.example.zad1_zjazd4;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String information) {
        super(information);
    }
}
