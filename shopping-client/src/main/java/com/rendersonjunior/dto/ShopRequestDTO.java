package com.rendersonjunior.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopRequestDTO {

    @NotBlank
    private String userIdentifier;

    private BigDecimal total;

    private LocalDateTime date;

    @Valid
    private List<ItemDTO> items;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemDTO {

        @NotBlank
        private String productIdentifier;
    }

}
