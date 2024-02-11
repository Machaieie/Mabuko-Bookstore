package com.livrariamabuko.Livraria.Mabuko.DTOs;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record PaymentDTO(
        Set<Long> salesIds,
        String type,
        double amount
) {

}
