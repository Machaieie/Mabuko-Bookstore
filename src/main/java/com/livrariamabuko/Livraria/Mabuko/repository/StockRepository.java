package com.livrariamabuko.Livraria.Mabuko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.model.StockBook;

public interface StockRepository extends JpaRepository<StockBook, Long> {
    
     StockBook  findByIdBook(long id);
}
