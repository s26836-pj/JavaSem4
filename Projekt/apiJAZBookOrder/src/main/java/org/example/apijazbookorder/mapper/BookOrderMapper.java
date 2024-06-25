package org.example.apijazbookorder.mapper;

import io.swagger.model.BookOrderRequest;
import io.swagger.model.BookOrderResponse;
import lombok.NonNull;
import org.example.apijazbookorder.model.BookOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BookOrderMapper {

    @Mapping(source = "bookId", target = "bookId", qualifiedByName = "intToUUID")
    BookOrderResponse mapBookEntityToResponse(BookOrder bookOrder);

    @Mapping(source = "bookId", target = "bookId", qualifiedByName = "UUIDToInt")
    BookOrder mapToEntity(BookOrderRequest bookOrderRequest);

    @Mapping(source = "bookId", target = "bookId", qualifiedByName = "UUIDToInt")
    void update(BookOrderRequest bookOrderRequest, @MappingTarget BookOrder bookOrder);

    @NonNull
    @Named("intToUUID")
    default UUID intToUUID(@NonNull int value) {
        return new UUID(0L, value);
    }

    @NonNull
    @Named("UUIDToInt")
    default int UUIDToInt(@NonNull UUID uuid) {
        return (int) uuid.getLeastSignificantBits();
    }
}
