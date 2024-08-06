package com.cstradic.open_pos.dtos;

import com.cstradic.open_pos.models.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String name;
    private int age;
    private Set<UserRole> roles = new HashSet<>();
}
