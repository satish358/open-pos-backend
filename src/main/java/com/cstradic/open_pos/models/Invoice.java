package com.cstradic.open_pos.models;

import com.cstradic.open_pos.utils.PaymentMode;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    @OneToMany
    private Set<PurchaseHistory> purchaseHistories;
    @Nullable
    private String gatewayResponseText;
    @Nullable
    private String gatewayTransactionId;
    private Double totalAmount;
    private PaymentMode paymentMode;
    private LocalDateTime createdOn;
    @ManyToOne @Nullable
    private Customer customer;
    @ManyToOne
    private User processedBy;
    @ManyToOne
    private Shop shop;


}
