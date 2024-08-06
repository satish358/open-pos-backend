package com.cstradic.open_pos.controllers;

import com.cstradic.open_pos.dtos.ResponseDTO;
import com.cstradic.open_pos.models.Shop;
import com.cstradic.open_pos.models.ShopInfo;
import com.cstradic.open_pos.services.ShopService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Shop Details Controller")
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity<ResponseDTO<ShopInfo>> createShop(@RequestBody Shop shop){
        ShopInfo shopInfo = shopService.createShop(shop);
        return new ResponseEntity<>(new ResponseDTO<>(shopInfo, "OK"), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ResponseDTO<ShopInfo>> updateShop(@RequestBody Shop shop){
        ShopInfo shopInfo = shopService.updateShop(shop);
        return new ResponseEntity<>(new ResponseDTO<>(shopInfo, "OK"), HttpStatus.OK);
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<ResponseDTO<ShopInfo>> getShop(@PathVariable("shopId") Long shopId) {
        ShopInfo shopInfo = shopService.getShopDetails(shopId);
        return ResponseEntity.ok(new ResponseDTO<>(shopInfo, "OK"));
    }
    @GetMapping
    public ResponseEntity<ResponseDTO<List<Shop>>> getAllShops() {
        List<Shop> shops = shopService.getAllShopDetails();
        return ResponseEntity.ok(new ResponseDTO<>(shops, "OK"));
    }
}
