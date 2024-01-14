package com.livrariamabuko.Livraria.Mabuko.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank  String ownerRef,
        @NotBlank @Email  String emailFrom,
        @NotBlank @Email  String emailTo,
        @NotBlank  String subject,
        @NotBlank  String text) {

}
