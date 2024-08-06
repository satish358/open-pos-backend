package com.cstradic.open_pos.repositories;

import com.cstradic.open_pos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByUidIgnoreCase(String uid);
}