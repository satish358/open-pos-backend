package com.cstradic.open_pos.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LoginRequestDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
