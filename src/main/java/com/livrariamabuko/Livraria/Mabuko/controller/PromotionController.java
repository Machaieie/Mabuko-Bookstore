package com.livrariamabuko.Livraria.Mabuko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.livrariamabuko.Livraria.Mabuko.DTOs.PromotionDTO;
import com.livrariamabuko.Livraria.Mabuko.model.Promotion;
import com.livrariamabuko.Livraria.Mabuko.service.PromotionService;

@RestController
@RequestMapping("/api/v1/")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/promotion")
    public ResponseEntity<List<Promotion>> addPromotion(@RequestBody List<PromotionDTO> promotionDTOs) {
        List<Promotion> promotions = promotionService.addPromotion(promotionDTOs);
        return new ResponseEntity<>(promotions, HttpStatus.CREATED);
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
