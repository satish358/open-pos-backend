package com.cstradic.open_pos.repositories;

import com.cstradic.open_pos.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByContactIgnoreCase(String contact);

    boolean existsByContactIgnoreCase(String contact);
}