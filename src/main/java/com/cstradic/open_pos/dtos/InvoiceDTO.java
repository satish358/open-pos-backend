package com.cstradic.open_pos.dtos;

import com.cstradic.open_pos.models.Customer;
import com.cstradic.open_pos.utils.PaymentMode;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
public class InvoiceDTO {
    @NotBlank
    private String uuid;
    private Set<ProductAbDTO> products;
    @Nullable
    private String gatewayResponseText;
    private String gatewayTransactionId;
    private PaymentMode paymentMode;
    private LocalDateTime createdOn;
    @Nullable
    private Customer customer;

}
