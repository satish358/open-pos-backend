package com.cstradic.open_pos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ChangePasswordDTO {
    private String newPassword;
}
