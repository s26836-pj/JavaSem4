package org.example.apijazbookshop.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import org.example.apijazbookshop.validation.ValidateBookType;

import java.util.UUID;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    private String title;
    @NotBlank
    @ValidateBookType
    private String genre;
    private double price;
    private int pages;
    private int visitCount;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
