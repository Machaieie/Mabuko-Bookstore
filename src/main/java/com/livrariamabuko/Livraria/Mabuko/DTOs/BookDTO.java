package com.livrariamabuko.Livraria.Mabuko.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookDTO(
        String title,

        String publisherDate,

        int edition,

        String gender,
        double price,

        long author_id,

        long publisher_id) {
}
