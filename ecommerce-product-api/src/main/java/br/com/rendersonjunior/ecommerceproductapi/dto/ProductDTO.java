package br.com.rendersonjunior.ecommerceproductapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank
        String productIdentifier,

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        Double preco,

        @NotNull
        CategoryDTO category) {
}
