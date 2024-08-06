package com.cstradic.open_pos.services;

import com.cstradic.open_pos.dtos.CreateShopUserDTO;
import com.cstradic.open_pos.exceptions.ShopAlreadyExistsException;
import com.cstradic.open_pos.exceptions.ShopNotExistsException;
import com.cstradic.open_pos.exceptions.UserNotFoundException;
import com.cstradic.open_pos.models.Shop;
import com.cstradic.open_pos.models.ShopInfo;
import com.cstradic.open_pos.models.ShopUser;
import com.cstradic.open_pos.models.User;
import com.cstradic.open_pos.repositories.ShopRepository;
import com.cstradic.open_pos.repositories.ShopUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopUserRepository shopUserRepository;
    @Autowired
    UserService userService;



    @Override
    public ShopInfo createShop(Shop shop) {
        if(shopRepository.existsByEmailIgnoreCase(shop.getEmail()))
            throw new ShopAlreadyExistsException();
        User user= userService.getCurrentUser().orElseThrow(UserNotFoundException::new);

        if(shopUserRepository.existsByUser_EmailIgnoreCase(user.getEmail()))
            throw new ShopAlreadyExistsException("One shop already added, not allowed to add multiple shops for one user.");
        shop.setId(null);
        shop.setCreatedAt(LocalDateTime.now());
        shopRepository.save(shop);

        shopUserRepository.save(new ShopUser(null, user, shop));
        return shop;
    }

    @Override
    public ShopInfo updateShop(Shop shop) {
        Shop dShop = shopRepository.findById(shop.getId()).orElseThrow(ShopNotExistsException::new);
        if((!dShop.getEmail().equalsIgnoreCase(shop.getEmail())) && shopRepository.existsByEmailIgnoreCase(shop.getEmail()))
            throw new ShopAlreadyExistsException("Email entered, Already registered with other shop.");
        shop.setCreatedAt(dShop.getCreatedAt());
        shopRepository.save(shop);

        return shop;
    }

    @Override
    public ShopInfo getShopDetails(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(ShopNotExistsException::new);
    }

    @Override
    public List<Shop> getAllShopDetails() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getCurrentUserShop() {
        var currentUser = userService.getCurrentUser().orElseThrow(UserNotFoundException::new);
        ShopUser shopUser = shopUserRepository.findByUser_EmailIgnoreCase(currentUser.getEmail()).orElseThrow(ShopNotExistsException::new);
        return shopUser.getShop();
    }
}
