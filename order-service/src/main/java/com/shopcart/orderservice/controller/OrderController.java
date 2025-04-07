package com.shopcart.orderservice.controller;

import com.shopcart.orderservice.dto.OrderDTO;
import com.shopcart.orderservice.dto.OrderWithPaymentDTO;
import com.shopcart.orderservice.entity.Order;
import com.shopcart.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/with-payment")
    public OrderWithPaymentDTO createOrderWithPayment(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrderWithPayment(orderDTO);
    }
}
