package com.livrariamabuko.Livraria.Mabuko.DTOs;

import jakarta.validation.constraints.NotBlank;

public record PublisherDTO(
        @NotBlank String name,
        @NotBlank String location,
        @NotBlank String nuit,
        @NotBlank String phone) {

}
