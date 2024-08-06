package com.cstradic.open_pos.repositories;

import com.cstradic.open_pos.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    boolean existsByNameIgnoreCase(String name);
}