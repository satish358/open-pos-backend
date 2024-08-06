package com.cstradic.open_pos.services;

import com.cstradic.open_pos.configs.JwtService;
import com.cstradic.open_pos.dtos.InvoiceDTO;
import com.cstradic.open_pos.exceptions.InvoiceNotFoundException;
import com.cstradic.open_pos.exceptions.ProductNotFoundException;
import com.cstradic.open_pos.exceptions.UserNotFoundException;
import com.cstradic.open_pos.models.*;
import com.cstradic.open_pos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopUserRepository shopUserRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;


    @Override
    public Invoice create(InvoiceDTO invoiceDTO, String token) {
        token = jwtService.extractToken(token);
        ShopUser shopUser = shopUserRepository.findByUser_EmailIgnoreCase(jwtService.extractUsername(token)).orElseThrow(UserNotFoundException::new);
        Set<PurchaseHistory> purchaseHistories = new HashSet<>();
        Double totalAmount =  invoiceDTO.getProducts().stream()
                .mapToDouble(pd -> {
                    var product = productRepository.findById(pd.getProductId()).orElseThrow(ProductNotFoundException::new);
                    var totalPrice = product.getSellingPrice() * pd.getUnits();
                    PurchaseHistory  purchaseHistory = new PurchaseHistory(null,product, product.getSellingPrice(), totalPrice, pd.getUnits() );
                    purchaseHistoryRepository.save(purchaseHistory);
                    purchaseHistories.add(purchaseHistory);
                    return totalPrice;
                }).sum();

        Customer customer = invoiceDTO.getCustomer() == null
                ? null
                : this.findOrCreateCustomer(invoiceDTO.getCustomer());
        Invoice invoice = new Invoice(
                null,
                invoiceDTO.getUuid(),
                purchaseHistories,
                invoiceDTO.getGatewayResponseText(),
                invoiceDTO.getGatewayTransactionId(),
                totalAmount,
                invoiceDTO.getPaymentMode(),
                LocalDateTime.now(),
                customer,
                shopUser.getUser(),
                shopUser.getShop());
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public Invoice findById(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(InvoiceNotFoundException::new);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer findCustomerByContact(String contact) {
        return customerRepository.findByContactIgnoreCase(contact).orElseThrow(()-> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer findOrCreateCustomer(Customer customer) {
        if(customerRepository.existsByContactIgnoreCase(customer.getContact()))
            return findCustomerByContact(customer.getContact());
        customer.setId(null);
        customerRepository.save(customer);
        return customer;
    }
}
