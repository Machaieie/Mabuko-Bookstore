package com.livrariamabuko.Livraria.Mabuko.service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.DTOs.PaymentDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Payment;
import com.livrariamabuko.Livraria.Mabuko.model.Sales;
import com.livrariamabuko.Livraria.Mabuko.repository.PaymentRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.SaleRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SaleRepository salesRepository;

    public Payment addPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setType(paymentDTO.type());
        payment.setAmount(paymentDTO.amount());
        payment.setDate(getCurrentDateTime());
    
        Set<Sales> salesSet = new HashSet<>();
        for (Long saleId : paymentDTO.salesIds()) {
            Sales sale = salesRepository.findById(saleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Sale with ID: " + saleId + " not found!"));
            salesSet.add(sale);
        }
        payment.setSales(salesSet);
    
        return paymentRepository.save(payment);
    }

     public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private String getCurrentDateTime() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    return now.format(formatter);
}
    
}
