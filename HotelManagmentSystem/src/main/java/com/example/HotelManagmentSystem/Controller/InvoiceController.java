package com.example.HotelManagmentSystem.Controller;

import com.example.HotelManagmentSystem.DTO.InvoiceDTO;
import com.example.HotelManagmentSystem.Entity.Invoice;
import com.example.HotelManagmentSystem.Service.InvoiceService;
import com.example.HotelManagmentSystem.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
        return ResponseEntity.ok(createdInvoice);
    }

    @PutMapping("/{invoiceId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO updatedInvoice = invoiceService.updateInvoice(invoiceId, invoiceDTO);
        if (updatedInvoice != null) {
            return ResponseEntity.ok(updatedInvoice);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/status/{invoiceId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<InvoiceDTO> updateInvoiceStatus(@PathVariable Long invoiceId, @RequestParam Invoice.Status newStatus) {
        InvoiceDTO updatedInvoice = invoiceService.updateInvoiceStatus(invoiceId, newStatus);
        return ResponseEntity.ok(updatedInvoice);
    }

    @DeleteMapping("/{invoiceId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceId) {
        if (invoiceService.deleteInvoice(invoiceId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{invoiceId}")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long invoiceId) {
        InvoiceDTO invoice = invoiceService.getInvoiceById(invoiceId);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/reservation/{reservationId}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByReservationId(@PathVariable Long reservationId) {
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByReservationId(reservationId);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByStatus(@PathVariable Invoice.Status status) {
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByStatus(status);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/user/{invoiceId}")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<User> getUserByInvoiceId(@PathVariable Long invoiceId) {
        User user = invoiceService.getUserByInvoiceId(invoiceId);
        return ResponseEntity.ok(user);
    }
}

