package com.shopcart.orderservice.service;

import com.shopcart.orderservice.dto.OrderDTO;
import com.shopcart.orderservice.dto.OrderWithPaymentDTO;
import com.shopcart.orderservice.dto.PaymentRequestDTO;
import com.shopcart.orderservice.dto.PaymentResponseDTO;
import com.shopcart.orderservice.entity.Order;
import com.shopcart.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @Value("${payment.service.url:http://localhost:8083/api/payments}")
    private String paymentServiceUrl;

    public OrderWithPaymentDTO createOrderWithPayment(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setProductIds(orderDTO.getProductIds());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order = orderRepository.save(order);

        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setOrderId(order.getId());
        paymentRequestDTO.setAmount(order.getTotalAmount());

        PaymentResponseDTO paymentResponseDTO = restTemplate.postForObject(paymentServiceUrl, paymentRequestDTO, PaymentResponseDTO.class);

        OrderWithPaymentDTO response = new OrderWithPaymentDTO();
        response.setOrder(order);
        response.setPayment(paymentResponseDTO);
        return response;
    }

    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setProductIds(orderDTO.getProductIds());
        order.setTotalAmount(orderDTO.getTotalAmount());
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

}
