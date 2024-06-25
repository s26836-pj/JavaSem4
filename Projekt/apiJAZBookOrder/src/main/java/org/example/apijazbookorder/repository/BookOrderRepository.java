package org.example.apijazbookorder.repository;

import org.example.apijazbookorder.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookOrderRepository extends JpaRepository<BookOrder, UUID> {

}
