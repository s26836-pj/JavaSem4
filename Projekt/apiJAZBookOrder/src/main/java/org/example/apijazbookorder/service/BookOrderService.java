package org.example.apijazbookorder.service;

import io.swagger.model.BookOrderRequest;
import io.swagger.model.BookOrderResponse;
import lombok.NonNull;
import org.example.apijazbookorder.exception.ResourceOutOfStockException;
import org.example.apijazbookorder.mapper.BookOrderMapper;
import org.example.apijazbookorder.model.BookOrder;
import org.example.apijazbookorder.repository.BookOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookOrderService {

    private final BookOrderRepository bookOrderRepository;
    private final BookOrderMapper bookOrderMapper;

    @Autowired
    public BookOrderService(BookOrderRepository bookOrderRepository, BookOrderMapper bookOrderMapper) {
        this.bookOrderRepository = bookOrderRepository;
        this.bookOrderMapper = bookOrderMapper;
    }

    public InputStreamResource printAllOrders() {
        List<BookOrder> orders = bookOrderRepository.findAll();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            for (BookOrder order : orders) {
                document.add(new Paragraph("Order ID: " + order.getId()));
                document.add(new Paragraph("Book ID: " + order.getBookId()));
                document.add(new Paragraph("Quantity: " + order.getQuantity()));
                document.add(new Paragraph("\n"));
            }

            document.close();

            return new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));
        } catch (Exception e) {
            throw new RuntimeException("Could not generate PDF", e);
        }
    }

    @NonNull
    public InputStreamResource printOrder(@NonNull UUID id) {
        BookOrder bookOrder = bookOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceOutOfStockException("Book order not found with id: " + id));

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Order ID: " + bookOrder.getId()));
            document.add(new Paragraph("Book ID: " + bookOrder.getBookId()));
            document.add(new Paragraph("Quantity: " + bookOrder.getQuantity()));
            document.close();

            return new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));
        } catch (Exception e) {
            throw new RuntimeException("Could not generate PDF", e);
        }
    }

    public List<BookOrder> getAllOrders() {
        return bookOrderRepository.findAll();
    }

    public List<BookOrderResponse> getAllBookOrders() {
        List<BookOrder> foundBookOrders = bookOrderRepository.findAll();
        return foundBookOrders.stream()
                .map(bookOrderMapper::mapBookEntityToResponse)
                .collect(Collectors.toList());
    }

    @NonNull
    public BookOrderResponse getBookOrderById(@NonNull UUID id) {
        BookOrder bookOrder = bookOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceOutOfStockException("Book order not found with id: " + id));
        return bookOrderMapper.mapBookEntityToResponse(bookOrder);
    }

    public BookOrderResponse createBookOrder(BookOrderRequest createRequest) {
        BookOrder bookOrder = bookOrderMapper.mapToEntity(createRequest);
        BookOrder savedBookOrder = bookOrderRepository.save(bookOrder);
        return bookOrderMapper.mapBookEntityToResponse(savedBookOrder);
    }

    @NonNull
    public BookOrderResponse updateBookOrder(@NonNull UUID id, BookOrderRequest updateRequest) {
        BookOrder bookOrder = bookOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceOutOfStockException("Book order not found with id: " + id));
        bookOrderMapper.update(updateRequest, bookOrder);
        return bookOrderMapper.mapBookEntityToResponse(bookOrderRepository.save(bookOrder));
    }

    @NonNull
    public void deleteBookOrder(@NonNull UUID id) {
        bookOrderRepository.deleteById(id);
    }
}
