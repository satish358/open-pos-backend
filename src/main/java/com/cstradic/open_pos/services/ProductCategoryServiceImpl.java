package com.cstradic.open_pos.services;

import com.cstradic.open_pos.exceptions.ProductCategoryNotFoundException;
import com.cstradic.open_pos.exceptions.ShopNotExistsException;
import com.cstradic.open_pos.models.ProductCategory;
import com.cstradic.open_pos.repositories.ProductCategoryRepository;
import com.cstradic.open_pos.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    ShopService shopService;

    @Override
    public ProductCategory create(ProductCategory productCategory) {
        productCategory.setId(null);
        var shop = shopService.getCurrentUserShop();
        productCategory.setShop(shop);
        productCategoryRepository.save(productCategory);
        return productCategory;
    }

    @Override
    public ProductCategory update(ProductCategory productCategory) {
        var productCt = productCategoryRepository.findById(productCategory.getId()).orElseThrow(ProductCategoryNotFoundException::new);
        productCt.setName(productCategory.getName());
        productCt.setDescription(productCategory.getDescription());
        productCategoryRepository.save(productCt);
        return productCt;
    }

    @Override
    public ProductCategory findById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId).orElseThrow(ProductCategoryNotFoundException::new);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }
}
