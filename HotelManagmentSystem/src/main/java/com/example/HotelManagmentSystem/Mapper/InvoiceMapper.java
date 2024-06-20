package com.example.HotelManagmentSystem.Mapper;

import com.example.HotelManagmentSystem.DTO.InvoiceDTO;
import com.example.HotelManagmentSystem.Entity.Invoice;
import com.example.HotelManagmentSystem.Entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {

    public Invoice toEntity(InvoiceDTO invoiceDTO, Reservation reservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceDTO.getInvoiceId());
        invoice.setReservation(reservation);
        invoice.setAmount(invoiceDTO.getAmount());
        invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
        invoice.setDueDate(invoiceDTO.getDueDate());
        invoice.setStatus(Invoice.Status.valueOf(invoiceDTO.getStatus()));
        return invoice;
    }

    public InvoiceDTO toDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceId(invoice.getInvoiceId());
        invoiceDTO.setReservationId(invoice.getReservation().getReservationId());
        invoiceDTO.setAmount(invoice.getAmount());
        invoiceDTO.setInvoiceDate(invoice.getInvoiceDate());
        invoiceDTO.setDueDate(invoice.getDueDate());
        invoiceDTO.setStatus(invoice.getStatus().name());
        return invoiceDTO;
    }

    public List<InvoiceDTO> toDTOList(List<Invoice> invoices) {
        return invoices.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
