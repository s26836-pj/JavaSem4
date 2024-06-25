package org.example.apijazbookshop.repository;

import org.example.apijazbookshop.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID>{

}
