package com.cstradic.open_pos.services;

import com.cstradic.open_pos.dtos.CreateShopUserDTO;
import com.cstradic.open_pos.exceptions.ShopNotExistsException;
import com.cstradic.open_pos.exceptions.UserAlreadyExistsException;
import com.cstradic.open_pos.models.ShopUser;
import com.cstradic.open_pos.models.User;
import com.cstradic.open_pos.repositories.ShopRepository;
import com.cstradic.open_pos.repositories.ShopUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopUserServiceImpl implements ShopUserService {
    @Autowired
    ShopUserRepository shopUserRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    UserService userService;

    @Override
    public ShopUser create(CreateShopUserDTO shopUserDTO) {
        var shop = shopRepository.findById(shopUserDTO.getShopId()).orElseThrow(ShopNotExistsException::new);
        if(shopUserRepository.existsByUser_EmailIgnoreCaseAndShop(shopUserDTO.getEmail(), shop))
            throw new UserAlreadyExistsException();
        if(shopUserRepository.existsByUser_EmailIgnoreCase(shopUserDTO.getEmail()))
            throw new UserAlreadyExistsException("User already added for another shop.");
        User user = userService.register(shopUserDTO);
        ShopUser shopUser = new ShopUser(null, user, shop);
        shopUserRepository.save(shopUser);
        return shopUser;
    }
}
