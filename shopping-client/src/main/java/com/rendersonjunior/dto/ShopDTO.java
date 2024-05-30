package com.rendersonjunior.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    @NotBlank
    private String userIdentifier;

    private BigDecimal total;

    private LocalDateTime date;

    @Valid
    private List<ItemDTO> items;

}
