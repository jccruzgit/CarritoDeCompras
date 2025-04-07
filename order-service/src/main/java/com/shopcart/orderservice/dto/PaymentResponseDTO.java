package com.shopcart.orderservice.dto;

import com.shopcart.orderservice.enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentResponseDTO {

    private Long paymentId;
    private PaymentStatus status;
    private String message;

}
