package com.livrariamabuko.Livraria.Mabuko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.repository.StockRepository;

@Service
public class StockService {
    
    @Autowired
    private StockRepository stockRepository;

    public Long countBooksInStock() {
        return stockRepository.countByAmountGreaterThan(0);
    }
}
