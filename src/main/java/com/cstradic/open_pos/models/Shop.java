package com.cstradic.open_pos.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Shop implements ShopInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String logoUrl;
    private String email;
    private String contact;
    private LocalDateTime createdAt;
//    @OneToOne
//    private User createdBy;
}

