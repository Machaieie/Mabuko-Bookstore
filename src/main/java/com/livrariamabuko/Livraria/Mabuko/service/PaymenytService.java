package com.livrariamabuko.Livraria.Mabuko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.repository.PaymentRepository;

@Service
public class PaymenytService {

    @Autowired
    private PaymentRepository paymentRepository;
    
}
