package com.cstradic.open_pos.dtos;

import com.cstradic.open_pos.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginResponseDTO {
    private String token;
    private User user;
}
