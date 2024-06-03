package org.example.zad1_zjazd4;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String destination;
}
