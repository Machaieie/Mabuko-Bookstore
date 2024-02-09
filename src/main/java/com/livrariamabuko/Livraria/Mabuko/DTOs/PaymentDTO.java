package com.livrariamabuko.Livraria.Mabuko.DTOs;

import java.util.Set;

public record PaymentDTO(
        Set<Long> salesIds,
        String type,
        double amount,
        String date

) {

}
