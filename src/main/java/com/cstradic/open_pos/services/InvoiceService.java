package com.cstradic.open_pos.services;

import com.cstradic.open_pos.dtos.InvoiceDTO;
import com.cstradic.open_pos.models.Customer;
import com.cstradic.open_pos.models.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {
    Invoice create(InvoiceDTO invoiceDTO,String token);
    Invoice findById(Long invoiceId);
    List<Invoice> findAll(); // TODO: Add pagination support
    Customer findCustomerById(Long customerId);
    Customer findCustomerByContact(String contact);
    Customer findOrCreateCustomer(Customer customer);
}
