package com.livrariamabuko.Livraria.Mabuko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.livrariamabuko.Livraria.Mabuko.DTOs.PromotionDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.DuplicatedEntityException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.model.Promotion;
import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.PromotionRepository;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;


    public Promotion updatePromotion(long id, PromotionDTO promotionDTO) {
        Promotion existingPromotion = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion with ID: " + id + " not found!"));

        existingPromotion.setDiscount(promotionDTO.discount());
        existingPromotion.setStartDate(promotionDTO.startDate());
        existingPromotion.setEndDate(promotionDTO.endDate());

        return promotionRepository.save(existingPromotion);
    }

    public void deletePromotion(long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion with ID: " + id + " not found!"));

        promotionRepository.delete(promotion);
    }

    public List<Promotion> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        if(promotions.isEmpty()){
            throw new EmptyDatabaseException("Não existe promocões na base de dados");
        }
        return promotions;
    }

    public Promotion getPromotionById(long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion with ID: " + id + " not found!"));
    }

    public boolean findByBookId(long id){
        Optional<Promotion> existingPromotion = promotionRepository.findByBookId(id);
        return existingPromotion.isPresent();
    }
    
}
