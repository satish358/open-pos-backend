package com.cstradic.open_pos.models;

import com.cstradic.open_pos.utils.ProductUnit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String uid;
    private Double purchasedPrice;
    private Double sellingPrice;
    private Double unitQuantity; // Product quantity like (if unit is kg and qnt 2 = 2kg)
    private Double quantity; // All product count
    private ProductUnit unit;
    @ManyToOne
    private Shop shop;
    @ManyToOne
    private ProductCategory productCategory;

    //TODO: Price change for invoice needs to be added.


}
