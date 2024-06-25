package org.example.apijazbookshop.mapper;

import org.example.apijazbookshop.dto.OrderDTO;
import org.example.apijazbookshop.model.Book;
import org.example.apijazbookshop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "book.id", target = "bookId")
    OrderDTO toOrderDTO(Order order);

    @Mapping(source = "bookId", target = "book", qualifiedByName = "bookFromId")
    Order toOrder(OrderDTO orderDTO);

    @Named("bookFromId")
    default Book map(UUID bookId) {
        Book book = new Book();
        book.setId(bookId);
        return book;
    }
}
