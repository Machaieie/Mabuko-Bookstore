package com.livrariamabuko.Livraria.Mabuko.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank String username,
        @NotBlank String password
) {
}
