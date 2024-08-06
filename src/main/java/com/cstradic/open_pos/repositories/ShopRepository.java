package com.cstradic.open_pos.repositories;

import com.cstradic.open_pos.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}