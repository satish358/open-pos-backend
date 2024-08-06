package com.cstradic.open_pos.repositories;

import com.cstradic.open_pos.models.Shop;
import com.cstradic.open_pos.models.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {
    boolean existsByUser_EmailIgnoreCaseAndShop(String email, Shop shop);

    boolean existsByUser_EmailIgnoreCase(String email);

    Optional<ShopUser> findByUser_EmailIgnoreCase(String email);
}