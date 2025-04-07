package com.shopcart.paymentservice.dto;

import com.shopcart.paymentservice.entity.PaymentStatus;
import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long paymentId;
    private String message;
    private PaymentStatus paymentStatus;
}
