package com.cstradic.open_pos.controllers;

import com.cstradic.open_pos.dtos.RegisterRequestDTO;
import com.cstradic.open_pos.dtos.ResponseDTO;
import com.cstradic.open_pos.models.ProductCategory;
import com.cstradic.open_pos.models.User;
import com.cstradic.open_pos.services.ProductCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Product Categories Controller")
@RestController
@RequestMapping("/productCategory")
@CrossOrigin("*")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseDTO<ProductCategory>> create(@RequestBody ProductCategory productCategory) {
        return new ResponseEntity<>(new ResponseDTO<>(
                productCategoryService.create(productCategory),
                "Product category created Successfully"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseDTO<ProductCategory>> update(@RequestBody ProductCategory productCategory) {
        return new ResponseEntity<>(new ResponseDTO<>(
                productCategoryService.update(productCategory),
                "Product category updated Successfully"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{productCategoryId}")
    public ResponseEntity<ResponseDTO<ProductCategory>> findById(@PathVariable Long productCategoryId) {
        return new ResponseEntity<>(new ResponseDTO<>(
                productCategoryService.findById(productCategoryId),
                "Product category for product category id = "+productCategoryId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseDTO<List<ProductCategory>>> findAll() {
        return new ResponseEntity<>(new ResponseDTO<>(
                productCategoryService.findAll(),
                "All Data"), HttpStatus.OK);
    }
}
