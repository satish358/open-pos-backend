package com.cstradic.open_pos.services;

import com.cstradic.open_pos.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product create(Product product);
    Product update(Product product);
    Product findById(Long productId);
    List<Product> findAll();
}
