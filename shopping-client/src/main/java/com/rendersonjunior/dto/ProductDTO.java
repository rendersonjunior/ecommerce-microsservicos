package com.rendersonjunior.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
        @NotBlank
        String productIdentifier;

        @NotBlank
        String nome;

        @NotBlank
        String descricao;

        @NotNull
        BigDecimal preco;

        @NotNull
        CategoryDTO category;
}
