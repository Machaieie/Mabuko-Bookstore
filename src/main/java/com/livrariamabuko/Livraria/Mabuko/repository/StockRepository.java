package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariamabuko.Livraria.Mabuko.model.StockBook;

public interface StockRepository extends JpaRepository<StockBook, Long> {
    
}
