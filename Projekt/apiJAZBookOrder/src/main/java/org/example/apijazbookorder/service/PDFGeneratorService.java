package org.example.apijazbookorder.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.apijazbookorder.model.BookOrder;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFGeneratorService {

    public void generatePDF(List<BookOrder> orders, FileOutputStream fos) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("order_summary.pdf"));

        document.open();
        document.add(new Paragraph("Order Summary"));

        for (BookOrder order : orders) {
            document.add(new Paragraph("Book ID: " + order.getBookId()));
            document.add(new Paragraph("Order Quantity: " + order.getQuantity()));
            document.add(new Paragraph("----------"));
        }

        document.close();
    }
}
