package com.livrariamabuko.Livraria.Mabuko.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import java.util.*;

public record BookDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank
        String publisherDate,
        
        @NotNull
        int edition,

        @NotBlank(message = "Gender is required")
        String gender,


       

        @NotNull(message = "Author is required")
        long author_id,

        @NotNull(message = "Publisher is required")
        long publisher_id) {
}
