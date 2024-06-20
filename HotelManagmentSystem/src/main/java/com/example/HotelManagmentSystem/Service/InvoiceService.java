package com.example.HotelManagmentSystem.Service;

import com.example.HotelManagmentSystem.DTO.InvoiceDTO;
import com.example.HotelManagmentSystem.Entity.Invoice;
import com.example.HotelManagmentSystem.Entity.Reservation;
import com.example.HotelManagmentSystem.Mapper.InvoiceMapper;
import com.example.HotelManagmentSystem.Repository.InvoiceRepository;
import com.example.HotelManagmentSystem.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(invoiceDTO.getReservationId());

        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            Invoice invoice = invoiceMapper.toEntity(invoiceDTO, reservation);
            Invoice savedInvoice = invoiceRepository.save(invoice);
            return invoiceMapper.toDTO(savedInvoice);
        } else {
            throw new IllegalArgumentException("Reservation not found");
        }
    }

    public InvoiceDTO updateInvoice(Long invoiceId, InvoiceDTO invoiceDTO) {
        Optional<Invoice> existingInvoiceOptional = invoiceRepository.findById(invoiceId);
        if (existingInvoiceOptional.isPresent()) {
            Invoice existingInvoice = existingInvoiceOptional.get();
            existingInvoice.setAmount(invoiceDTO.getAmount());
            existingInvoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
            existingInvoice.setDueDate(invoiceDTO.getDueDate());
            existingInvoice.setStatus(Invoice.Status.valueOf(invoiceDTO.getStatus()));
            Invoice updatedInvoice = invoiceRepository.save(existingInvoice);
            return invoiceMapper.toDTO(updatedInvoice);
        } else {
            throw new IllegalArgumentException("Invoice not found");
        }
    }

    public boolean deleteInvoice(Long invoiceId) {
        if (invoiceRepository.existsById(invoiceId)) {
            invoiceRepository.deleteById(invoiceId);
            return true;
        }
        return false;
    }

    public InvoiceDTO getInvoiceById(Long invoiceId) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        return invoiceOptional.map(invoiceMapper::toDTO).orElse(null);
    }

    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoiceMapper.toDTOList(invoices);
    }

    public List<InvoiceDTO> getInvoicesByReservationId(Long reservationId) {
        List<Invoice> invoices = invoiceRepository.findByReservation_ReservationId(reservationId);
        return invoiceMapper.toDTOList(invoices);
    }

    public List<InvoiceDTO> getInvoicesByStatus(Invoice.Status status) {
        List<Invoice> invoices = invoiceRepository.findByStatus(status);
        return invoiceMapper.toDTOList(invoices);
    }
}
