package org.example.zad1_zjazd4;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Setter
@Getter
@Data
@Entity
public class Passenger {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private int age;

    @ManyToMany
    @JoinTable(
            name = "Passenger_Flight",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private List<Flight> flights;
}