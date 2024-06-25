package org.example.apijazbookorder.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


import java.util.UUID;


@Data
@Entity
public class BookOrder {
    @Id
    @GeneratedValue
    private UUID id;
    private int bookId;
    private int quantity;


}