package com.livrariamabuko.Livraria.Mabuko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livrariamabuko.Livraria.Mabuko.model.Sales;

@Repository
public interface SaleRepository extends JpaRepository<Sales, Long> {
    
}
