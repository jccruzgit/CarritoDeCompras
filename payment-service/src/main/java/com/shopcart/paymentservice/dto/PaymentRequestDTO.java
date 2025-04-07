package com.shopcart.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long orderId;
    private Double amount;
}
