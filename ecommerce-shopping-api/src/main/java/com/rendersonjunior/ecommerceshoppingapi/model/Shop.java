package com.rendersonjunior.ecommerceshoppingapi.model;


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

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "USER_IDENTIFIER")
    private String userIdentifier;

    @Column(name = "TOTAL")
    private Double total;

    @Column(name = "DATE")
    private LocalDateTime date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

}
