package com.livrariamabuko.Livraria.Mabuko.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
         String username,
         String password
) {
}
