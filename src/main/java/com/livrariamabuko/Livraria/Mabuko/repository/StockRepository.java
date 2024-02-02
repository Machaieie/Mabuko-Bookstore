package com.livrariamabuko.Livraria.Mabuko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.model.StockBook;

@Repository
public interface StockRepository extends JpaRepository<StockBook, Long> {
    
     StockBook  findByBookId(long id);

     Long countByAmountGreaterThan(int amount);
}
