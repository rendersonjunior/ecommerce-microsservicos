package br.com.rendersonjunior.ecommerceproductapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_product")
    @SequenceGenerator(name = "sq_product", sequenceName = "sq_product", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "product_identifier")
    private String productIdentifier;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    @PrePersist
    private void insert() {
        this.dataCadastro = LocalDateTime.now();
    }

    @PreUpdate
    private void update() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
