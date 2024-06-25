package org.example.apijazbookshop.repository;

import org.example.apijazbookshop.exception.ResourceNotFoundException;
import org.example.apijazbookshop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookShopRepository extends JpaRepository<Book, UUID> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor_Name(String author);
    List<Book> findByGenre(String genre);

    default Book findOrThrowException(UUID uuid){
        return findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }


}
