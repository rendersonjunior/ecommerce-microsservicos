package com.rendersonjunior.ecommerceshoppingapi.repository;

import com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findAllByUserIdentifier(String userIdentifier);

    List<Shop> findAllByTotalGreaterThan(BigDecimal total);

    List<Shop> findAllByDateGreaterThan(LocalDateTime date);

}
