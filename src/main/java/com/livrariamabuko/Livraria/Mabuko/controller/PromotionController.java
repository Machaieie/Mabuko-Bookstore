package com.livrariamabuko.Livraria.Mabuko.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.livrariamabuko.Livraria.Mabuko.DTOs.PromotionDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.DuplicatedEntityException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Book;
import com.livrariamabuko.Livraria.Mabuko.model.Promotion;
import com.livrariamabuko.Livraria.Mabuko.repository.BookRepository;
import com.livrariamabuko.Livraria.Mabuko.repository.PromotionRepository;
import com.livrariamabuko.Livraria.Mabuko.service.PromotionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private  BookRepository  bookRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @PostMapping("/promotion")
    public ResponseEntity addPromotion(@Valid @RequestBody PromotionDTO promotionDTO)throws ResourceNotFoundException{
         Book book = bookRepository.findById(promotionDTO.book_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Book with ID: " + promotionDTO.book_id() + " not found!"));
     boolean existsPromotion = promotionService.findByBookId(promotionDTO.book_id());
     if (existsPromotion) {
        throw new DuplicatedEntityException("O livro já esta em promocão","");
     } 
     
     Promotion savePromotion = new Promotion();
     savePromotion.setBook(book);
     BeanUtils.copyProperties(promotionDTO, savePromotion);
     Promotion promotion = promotionRepository.save(savePromotion);
     return ResponseEntity.status(HttpStatus.CREATED).body("Promotion successfully registered!");
    }


    @PutMapping("/promotion/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable long id, @RequestBody PromotionDTO promotionDTO) {
        Promotion updatedPromotion = promotionService.updatePromotion(id, promotionDTO);
        return new ResponseEntity<>(updatedPromotion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable long id) {
        promotionService.deletePromotion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotions();
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("promotion/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable long id) {
        Promotion promotion = promotionService.getPromotionById(id);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }
}
