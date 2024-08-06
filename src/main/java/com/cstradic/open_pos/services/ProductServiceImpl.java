package com.cstradic.open_pos.services;

import com.cstradic.open_pos.exceptions.ProductAlreadyExistsException;
import com.cstradic.open_pos.exceptions.ProductCategoryNotFoundException;
import com.cstradic.open_pos.exceptions.ProductNotFoundException;
import com.cstradic.open_pos.models.Product;

import com.cstradic.open_pos.repositories.ProductCategoryRepository;
import com.cstradic.open_pos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @Override
    public Product create(Product product) {
        if(productRepository.existsByUidIgnoreCase(product.getUid()))
            throw new ProductAlreadyExistsException();
        product.setId(null);
        var productCategory = productCategoryRepository.findById(product.getProductCategory().getId())
                .orElseThrow(ProductCategoryNotFoundException::new);
        product.setProductCategory(productCategory);
        product.setShop(productCategory.getShop());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        if(!productRepository.existsById(product.getId()))
            throw new ProductNotFoundException();
        productRepository.save(product);
        return product;
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
