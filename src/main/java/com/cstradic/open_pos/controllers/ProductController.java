package com.cstradic.open_pos.controllers;

import com.cstradic.open_pos.dtos.ResponseDTO;
import com.cstradic.open_pos.models.Product;
import com.cstradic.open_pos.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Controller")
@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseDTO<Product>> create(@RequestBody Product product) {
        return new ResponseEntity<>(new ResponseDTO<>(
                productService.create(product),
                "Product  created Successfully"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseDTO<Product>> update(@RequestBody Product product) {
        return new ResponseEntity<>(new ResponseDTO<>(
                productService.update(product),
                "Product updated Successfully"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{productCategoryId}")
    public ResponseEntity<ResponseDTO<Product>> findById(@PathVariable("productCategoryId") Long productId) {
        return new ResponseEntity<>(new ResponseDTO<>(
                productService.findById(productId),
                "Product category for product category id = "+productId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseDTO<List<Product>>> findAll() {
        return new ResponseEntity<>(new ResponseDTO<>(
                productService.findAll(),
                "All Data"), HttpStatus.OK);
    }

}
