package org.example.apijazbookshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String surname;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
