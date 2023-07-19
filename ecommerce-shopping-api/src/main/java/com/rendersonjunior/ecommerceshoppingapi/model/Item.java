package com.rendersonjunior.ecommerceshoppingapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {

    @Column(name = "PRODUCT_IDENTIFIER")
    private String productIdentifier;

    @Column(name = "PRECO")
    private Double price;
}
