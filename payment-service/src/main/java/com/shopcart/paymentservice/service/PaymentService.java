package com.shopcart.paymentservice.service;

import com.shopcart.paymentservice.dto.PaymentRequestDTO;
import com.shopcart.paymentservice.dto.PaymentResponseDTO;
import com.shopcart.paymentservice.entity.Payment;
import com.shopcart.paymentservice.entity.PaymentStatus;
import com.shopcart.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setPaymentTime(LocalDateTime.now());

        //Simulacion de éxito/fallo aleatorio
        PaymentStatus paymentStatus = new Random().nextBoolean() ? PaymentStatus.SUCCESS : PaymentStatus.FAILURE;
        payment.setPaymentStatus(paymentStatus);

        Payment savedPayment = paymentRepository.save(payment);

        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setPaymentId(savedPayment.getId());
        paymentResponseDTO.setPaymentStatus(savedPayment.getPaymentStatus());
        paymentResponseDTO.setMessage(paymentStatus == PaymentStatus.SUCCESS ? "Payment successful" : "Payment failed");

        return paymentResponseDTO;

    }
}
