package com.example.HotelManagmentSystem.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private Long invoiceId;
    private Long reservationId;
    private BigDecimal amount;
    private Date invoiceDate;
    private Date dueDate;
    private String status;
}
