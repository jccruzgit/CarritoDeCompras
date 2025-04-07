package com.shopcart.paymentservice.controller;

import com.shopcart.paymentservice.dto.PaymentRequestDTO;
import com.shopcart.paymentservice.dto.PaymentResponseDTO;
import com.shopcart.paymentservice.entity.Payment;
import com.shopcart.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponseDTO pay(@RequestBody PaymentRequestDTO requestDTO) {
        return paymentService.processPayment(requestDTO);
    }
}
