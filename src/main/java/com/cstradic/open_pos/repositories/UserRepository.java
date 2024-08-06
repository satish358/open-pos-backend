package com.cstradic.open_pos.repositories;

import com.cstradic.open_pos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndVerifiedFalse(String email);


    boolean existsByActiveFalseAndEmailIgnoreCase(String email);
}