package com.livrariamabuko.Livraria.Mabuko.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AuthorDTO(

@NotBlank String name,
@NotBlank String biography,
@NotBlank String nationality
) {
    
}
