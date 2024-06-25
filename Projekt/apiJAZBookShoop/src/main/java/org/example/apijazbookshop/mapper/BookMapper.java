package org.example.apijazbookshop.mapper;


import io.swagger.model.BookRequest;
import io.swagger.model.BookResponse;
import org.example.apijazbookshop.model.Author;
import org.example.apijazbookshop.model.Book;
import org.hibernate.sql.Update;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "author", qualifiedByName = "getAuthor")
    Book mapToEntity(BookRequest bookRequest);
    @Mapping(target = "author", source = "author", qualifiedByName = "getAuthorName")
    BookResponse mapBookEntityToResponse(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "author", qualifiedByName = "getAuthor")
    Book update(BookRequest bookRequest, @MappingTarget Book book);


    @Named("getAuthor")
    default Author getAuthor(String authorName) {
        if (authorName != null) {
            Author author = new Author();
            author.setName(authorName);
            return author;
        }
        return null;
    }






    @Named("getAuthorName")
    default String getAuthorName(Author author) {
        return (author != null) ? author.getName() : null;
    }


}

