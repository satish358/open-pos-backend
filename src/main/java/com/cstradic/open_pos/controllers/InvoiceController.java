package com.cstradic.open_pos.controllers;

import com.cstradic.open_pos.dtos.InvoiceDTO;
import com.cstradic.open_pos.dtos.ResponseDTO;
import com.cstradic.open_pos.models.Customer;
import com.cstradic.open_pos.models.Invoice;
import com.cstradic.open_pos.services.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Invoice Service Controller")
@RestController
@CrossOrigin("*")
public class InvoiceController  {
    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/invoice")
    public ResponseEntity<ResponseDTO<Invoice>> create(@RequestBody InvoiceDTO invoiceDTO, @RequestHeader(name="Authorization") String token) {
        return new ResponseEntity<>(new ResponseDTO<>(invoiceService.create(invoiceDTO, token), "Successfully Invoice created."), HttpStatus.CREATED);
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<ResponseDTO<Invoice>> findById(@PathVariable("invoiceId") Long invoiceId) {
        return ResponseEntity.ok(new ResponseDTO<>(invoiceService.findById(invoiceId), "Invoice details"));
    }

    @GetMapping("/invoice")
    public ResponseEntity<ResponseDTO<List<Invoice>>> findAll() {
        return ResponseEntity.ok(new ResponseDTO<>(invoiceService.findAll(),"All Invoices"));
    }

    @GetMapping("/customer/findByCustomerId/{customerId}")
    public ResponseEntity<ResponseDTO<Customer>> findCustomerById(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(new ResponseDTO<>(invoiceService.findCustomerById(customerId), "Customer Details"));
    }
    @GetMapping("/customer/findByCustomerContact/{contact}")
    public ResponseEntity<ResponseDTO<Customer>> findCustomerByContact(@PathVariable("contact") String contact) {
        return ResponseEntity.ok(new ResponseDTO<>(invoiceService.findCustomerByContact(contact), "Customer Details"));
    }
}
