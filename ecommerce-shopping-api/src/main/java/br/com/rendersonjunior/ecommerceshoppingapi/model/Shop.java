package br.com.rendersonjunior.ecommerceshoppingapi.model;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "shop")
public class Shop implements Comparable<Shop> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_identifier")
    private String userIdentifier;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "date")
    private LocalDateTime date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

    @Override
    public String toString() {
        return String.format(
                "Compra > Cód. Usuário: %s, Total: %.2f, Data: %tF",
                userIdentifier,
                total,
                date);
    }

    @Override
    public int compareTo(Shop o) {
        return this.date.compareTo(o.date);
    }

}