package com.shopcart.orderservice.dto;

import com.shopcart.orderservice.entity.Order;
import lombok.Data;

@Data
public class OrderWithPaymentDTO {

    private Order order;
    private PaymentResponseDTO payment;

}
