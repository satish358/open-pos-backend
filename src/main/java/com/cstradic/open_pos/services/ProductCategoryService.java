package com.cstradic.open_pos.services;

import com.cstradic.open_pos.models.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCategoryService {
    ProductCategory create(ProductCategory productCategory);
    ProductCategory update(ProductCategory productCategory);
    ProductCategory findById(Long productCategoryId);
    List<ProductCategory> findAll();
}
