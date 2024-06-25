package org.example.apijazbookshop.service;

import io.swagger.model.BookOrderRequest;
import io.swagger.model.BookRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.example.apijazbookshop.exception.ResourceNotFoundException;
import org.example.apijazbookshop.feignClient.BookOrderFeignClient;
import org.example.apijazbookshop.mapper.BookMapper;
import org.example.apijazbookshop.model.Book;
import org.example.apijazbookshop.repository.OrderRepository;
import org.example.apijazbookshop.repository.BookShopRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Data
public class BookService {

    private final BookShopRepository bookShopRepository;
    private final BookMapper bookMapper;
    private final OrderRepository orderRepository;
    private final BookOrderFeignClient bookOrderFeignClient;


    // Show all books
    public List<Book> getAllBooks() {
        return bookShopRepository.findAll();
    }

    // Find by title
    @NotNull
    public List<Book> getBooksByTitle(@NotBlank String title) {
        return bookShopRepository.findByTitle(title);
    }

    // Find by author
    @NonNull
    public List<Book> getBooksByAuthor(@NotBlank String author) {
        return bookShopRepository.findByAuthor_Name(author);
    }

    // Find by genre
    @NonNull
    public List<Book> getBookByGenre(@NotBlank String genre) {
        return bookShopRepository.findByGenre(genre);
    }

    @NonNull
    public double getBookPrice(@NonNull UUID uuid) {
        Book book = bookShopRepository.findById(uuid).orElseThrow(() ->
                new ResourceNotFoundException("Book not found with id " + uuid));
        return book.getPrice();
    }

    // Generates book orders based on visit count and sends them using Feign client
    public void generateBookOrders() {
        List<Book> books = bookShopRepository.findAll();
        books.stream()
                .filter(book -> book.getVisitCount() / 10 > 0)
                .forEach(book ->
                        bookOrderFeignClient.createBookOrder(createBookOrderRequest(book))
                );
    }

    @NotNull
    public BookOrderRequest createBookOrderRequest(@NotNull Book book) {
        int quantityToOrder = book.getVisitCount() / 10;
        BookOrderRequest bookOrderRequest = new BookOrderRequest();
        bookOrderRequest.setBookId(book.getId());
        bookOrderRequest.setQuantity(quantityToOrder);
        return bookOrderRequest;
    }

    // For Admin only!
    public Book addBook(Book book) {
        return bookShopRepository.save(book);
    }

    @Transactional
    @NonNull
    public Book updateBook(@NotNull UUID uuid, BookRequest bookRequest) {
        Book findOrThrowException = bookShopRepository.findOrThrowException(uuid);

        return bookMapper.update(bookRequest, findOrThrowException);
    }


    public void deleteBook(@NotNull UUID uuid) {
        Book book = bookShopRepository.findById(uuid).orElseThrow(() ->
                new ResourceNotFoundException("Book not found"));
        bookShopRepository.delete(book);
    }
}
