package br.com.rendersonjunior.ecommerceproductapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
        Double preco;

        @NotNull
        CategoryDTO category;
}
