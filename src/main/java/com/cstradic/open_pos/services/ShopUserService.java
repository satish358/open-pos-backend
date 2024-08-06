package com.cstradic.open_pos.services;

import com.cstradic.open_pos.dtos.CreateShopUserDTO;
import com.cstradic.open_pos.models.ShopUser;
import org.springframework.stereotype.Service;

@Service
public interface ShopUserService {
    ShopUser create(CreateShopUserDTO shopUserDTO);

}
