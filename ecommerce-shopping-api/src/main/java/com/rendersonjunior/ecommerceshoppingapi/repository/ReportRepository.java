package com.rendersonjunior.ecommerceshoppingapi.repository;

import com.rendersonjunior.dto.ShopReportDTO;
import com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReportRepository extends JpaRepository<Shop, Long> {

    @Query("""
        select new com.rendersonjunior.dto.ShopReportDTO(count(s.id), sum(s.total), avg(s.total))
          from Shop s
        where s.date >= :dataInicio
          and s.date <= :dataFim
        """)
    ShopReportDTO getReportByDate(@Param("dataInicio") final LocalDateTime dataInicio,
                                  @Param("dataFim") final LocalDateTime dataFim);

}
