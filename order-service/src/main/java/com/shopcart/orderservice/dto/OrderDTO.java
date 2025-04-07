package com.shopcart.orderservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private List<Long> productIds;
    private Double totalAmount;
}
