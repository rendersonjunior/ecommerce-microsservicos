package com.rendersonjunior.ecommerceshoppingapi.repository;

import com.rendersonjunior.ecommerceshoppingapi.dto.ShopReportDTO;
import com.rendersonjunior.ecommerceshoppingapi.model.Shop;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository {

    List<Shop> getShopByFilters(LocalDateTime dataInicio, LocalDateTime dataFim, BigDecimal valorMinimo);
    ShopReportDTO getReportByDate(LocalDateTime dataInicio, LocalDateTime dataFim);

}
