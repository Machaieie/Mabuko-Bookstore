package com.livrariamabuko.Livraria.Mabuko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livrariamabuko.Livraria.Mabuko.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long>{
    Optional <Promotion> findByBookId(long id);
}
