package com.livrariamabuko.Livraria.Mabuko.DTOs;

import com.livrariamabuko.Livraria.Mabuko.model.Role;
import jakarta.validation.constraints.NotBlank;

public record SignUpDTO(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name,
        boolean passwordChanged,
        boolean mobileUser,
        Role role
) {
}
