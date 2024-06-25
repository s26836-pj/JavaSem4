package org.example.apijazbookshop.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class OrderDTO {
    private UUID id;
    private UUID bookId;
    private int quantity;
}
