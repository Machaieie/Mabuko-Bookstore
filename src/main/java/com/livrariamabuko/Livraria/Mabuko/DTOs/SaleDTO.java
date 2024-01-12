package com.livrariamabuko.Livraria.Mabuko.DTOs;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public record SaleDTO(
        long book_id,
        int amount,
       @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
       ZonedDateTime saleDate
) {

}
