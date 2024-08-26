package br.com.rendersonjunior.ecommerceshoppingapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item implements Comparable<Item> {

    @Column(name = "PRODUCT_IDENTIFIER")
    private String productIdentifier;

    @Column(name = "PRECO")
    private BigDecimal price;

    @Override
    public String toString() {
        return String.format(
                "Item Compra > Cód. Produto: %s, Preço: %.2f",
                productIdentifier,
                price);
    }

    @Override
    public int compareTo(Item o) {
        return this.price.compareTo(o.price);
    }
}