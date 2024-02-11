package com.livrariamabuko.Livraria.Mabuko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.livrariamabuko.Livraria.Mabuko.DTOs.PaymentDTO;
import com.livrariamabuko.Livraria.Mabuko.model.Payment;
import com.livrariamabuko.Livraria.Mabuko.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<Payment> addPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.addPayment(paymentDTO);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
