package org.example.apijazbookorder.controller;

import io.swagger.model.BookOrderRequest;
import io.swagger.model.BookOrderResponse;
import org.example.apijazbookorder.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book-orders")
public class BookOrderController {

    private final BookOrderService bookOrderService;

    @Autowired
    public BookOrderController(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @GetMapping("/print-all")
    public ResponseEntity<InputStreamResource> printAllOrders() {
        InputStreamResource resource = bookOrderService.printAllOrders();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/print/{id}")
    public ResponseEntity<InputStreamResource> printOrder(@PathVariable UUID id) {
        InputStreamResource resource = bookOrderService.printOrder(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping
    public ResponseEntity<List<BookOrderResponse>> getAllBookOrders() {
        List<BookOrderResponse> responses = bookOrderService.getAllBookOrders();
        if (responses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookOrderResponse> getBookOrderById(@PathVariable UUID id) {
        BookOrderResponse response = bookOrderService.getBookOrderById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookOrderResponse> createBookOrder(@RequestBody BookOrderRequest createRequest) {
        BookOrderResponse response = bookOrderService.createBookOrder(createRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookOrderResponse> updateBookOrder(@PathVariable UUID id, @RequestBody BookOrderRequest updateRequest) {
        BookOrderResponse response = bookOrderService.updateBookOrder(id, updateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookOrder(@PathVariable UUID id) {
        bookOrderService.deleteBookOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
