package com.livrariamabuko.Livraria.Mabuko.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BookDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "Publisher date is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date publisherDate,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotNull(message = "Availability is required")
        boolean availability,

        @NotBlank(message = "Author is required")
        String author,

        @NotBlank(message = "Publisher is required")
        String publisher) {
}
