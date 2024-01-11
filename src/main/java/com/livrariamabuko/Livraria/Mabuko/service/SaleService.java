package com.livrariamabuko.Livraria.Mabuko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.SaleRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.StockRepository;


@Service
public class SaleService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StockRepository stockRepository;


    
   

}
