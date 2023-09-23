package com.rendersonjunior.ecommerceshoppingapi.dto;

import jakarta.persistence.PrePersist;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    @NotBlank
    private String userIdentifier;

    @NotNull
    private Double total;

    @NotNull
    private LocalDateTime date;

    @Valid
    private List<ItemDTO> items;

    @PrePersist
    private void insert() {
        this.date = LocalDateTime.now();
    }

}
