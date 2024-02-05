package com.livrariamabuko.Livraria.Mabuko.DTOs;

public record PromotionDTO(
    double discount,
    String startDate,
    String endDate,
    long book_id

) {
    
}
