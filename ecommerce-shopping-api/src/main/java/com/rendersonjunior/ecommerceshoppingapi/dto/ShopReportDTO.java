package com.rendersonjunior.ecommerceshoppingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopReportDTO {

    private Long count;
    private BigDecimal total;
    private BigDecimal mean;

}
