package com.livrariamabuko.Livraria.Mabuko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.repository.PromotionRepository;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    
}
