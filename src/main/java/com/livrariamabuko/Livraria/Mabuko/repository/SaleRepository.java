package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariamabuko.Livraria.Mabuko.model.Sales;

public interface SaleRepository extends JpaRepository<Sales, Long> {
    
}
