package com.example.HotelManagmentSystem.Controller;

import com.example.HotelManagmentSystem.DTO.InvoiceDTO;
import com.example.HotelManagmentSystem.Entity.Invoice;
import com.example.HotelManagmentSystem.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
        return ResponseEntity.ok(createdInvoice);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO updatedInvoice = invoiceService.updateInvoice(invoiceId, invoiceDTO);
        if (updatedInvoice != null) {
            return ResponseEntity.ok(updatedInvoice);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceId) {
        if (invoiceService.deleteInvoice(invoiceId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long invoiceId) {
        InvoiceDTO invoice = invoiceService.getInvoiceById(invoiceId);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByReservationId(@PathVariable Long reservationId) {
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByReservationId(reservationId);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByStatus(@PathVariable Invoice.Status status) {
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByStatus(status);
        return ResponseEntity.ok(invoices);
    }
}
