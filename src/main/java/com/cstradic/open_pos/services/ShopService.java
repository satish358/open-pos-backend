package com.cstradic.open_pos.services;

import com.cstradic.open_pos.models.Shop;
import com.cstradic.open_pos.models.ShopInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopService {
    ShopInfo createShop(Shop shop);
    ShopInfo updateShop(Shop shop);
    ShopInfo getShopDetails(Long shopId);
    List<Shop> getAllShopDetails();
    Shop getCurrentUserShop();
}
