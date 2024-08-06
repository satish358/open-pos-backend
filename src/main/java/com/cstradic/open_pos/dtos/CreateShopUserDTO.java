package com.cstradic.open_pos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class CreateShopUserDTO extends RegisterRequestDTO {
    private Long shopId;
}
