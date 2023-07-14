package br.com.rendersonjunior.ecommerceproductapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDTO (
   @NotNull
   long id,

   @NotBlank
   String nome) {}
