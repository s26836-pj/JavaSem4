package org.example.apijazbookshop.controller;


import io.swagger.api.DefaultApi;
import io.swagger.model.BookRequest;
import io.swagger.model.BookResponse;
import org.example.apijazbookshop.exception.ResourceNotFoundException;
import org.example.apijazbookshop.mapper.BookMapper;
import org.example.apijazbookshop.model.Book;
import org.example.apijazbookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController implements DefaultApi {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @Autowired
    public BookController(BookMapper bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return books.stream()
                    .map(bookMapper::mapBookEntityToResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing books", e);
        }
    }

    @GetMapping("/title/{title}")
    public List<BookResponse> getBookByTitle(@PathVariable String title) {
        try {
            List<Book> books = bookService.getBooksByTitle(title);
            return books.stream()
                    .map(bookMapper::mapBookEntityToResponse)
                    .collect(Collectors.toList());
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/genre/{genre}")
    public List<BookResponse> getBookByGenre(@PathVariable String genre) {
        try {
            List<Book> books = bookService.getBookByGenre(genre);
            return books.stream()
                    .map(bookMapper::mapBookEntityToResponse)
                    .collect(Collectors.toList());
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/author/{author}")
    public List<BookResponse> getBookByAuthor(@PathVariable String author) {
        try {
            List<Book> books = bookService.getBooksByAuthor(author);
            return books.stream()
                    .map(bookMapper::mapBookEntityToResponse)
                    .collect(Collectors.toList());
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/price/{uuid}")
    public ResponseEntity<Double> getBookPrice(@PathVariable UUID uuid) {
        try {
            double price = bookService.getBookPrice(uuid);
            return ResponseEntity.ok(price);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    @PostMapping("/orders")
    public BookResponse createBookOrder(@RequestBody BookRequest bookRequest) {
        try {
            Book book = bookMapper.mapToEntity(bookRequest);
            Book addedBook = bookService.addBook(book);
            return bookMapper.mapBookEntityToResponse(addedBook);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    @PutMapping("/{uuid}")
    public BookResponse updateBook(@PathVariable UUID uuid, @RequestBody BookRequest bookRequest, BookResponse bookResponse) {
        try {
            Book updatedBook = bookService.updateBook(uuid, bookRequest);
            bookResponse = bookMapper.mapBookEntityToResponse(updatedBook);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
        return bookResponse;
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID uuid) {
        try {
            bookService.deleteBook(uuid);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", ex);
        }
        return null;
    }
}
