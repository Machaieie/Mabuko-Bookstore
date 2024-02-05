package com.livrariamabuko.Livraria.Mabuko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.livrariamabuko.Livraria.Mabuko.DTOs.PromotionDTO;
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

    @Autowired
    private BookRepository bookRepository;

    public List<Promotion> addPromotion(List<PromotionDTO> promoList){
        List<Promotion> promotions = new ArrayList<>();

        for (PromotionDTO promotionDTO : promoList) {
            Book book = bookRepository.findById(promotionDTO.book_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Book with ID: " + promotionDTO.book_id() + " not found!"));

            Promotion promotion = new Promotion();
            promotion.setDiscount(promotionDTO.discount());
            promotion.setStartDate(promotionDTO.startDate());
            promotion.setEndDate(promotionDTO.endDate());
            promotion.getBooks().add(book); 

            promotions.add(promotion); 

            promotionRepository.save(promotion); 
        }
        return promotions;

    }

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
    
}
