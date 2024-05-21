package com.rendersonjunior.ecommerceshoppingapi.service.shop;

import com.rendersonjunior.dto.ItemDTO;
import com.rendersonjunior.dto.ShopDTO;
import com.rendersonjunior.dto.ShopReportDTO;
import com.rendersonjunior.ecommerceshoppingapi.mapper.ShopMapper;
import com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import com.rendersonjunior.ecommerceshoppingapi.repository.ReportRepository;
import com.rendersonjunior.ecommerceshoppingapi.repository.ShopRepository;
import com.rendersonjunior.ecommerceshoppingapi.repository.specification.SpecificationShopByFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService implements IShopService {

    private final ShopRepository shopRepository;

    private final ReportRepository reportRepository;

    private final ShopMapper shopMapper;

    public List<ShopDTO> getAll() {
        return shopRepository.findAll()
                .stream()
                .sorted()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(final String userIdentifier) {
        return shopRepository.findAllByUserIdentifier(userIdentifier)
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(final ShopDTO shopDTO) {
        return shopRepository.findAllByDateGreaterThan(shopDTO.getDate())
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByValue(final BigDecimal value) {
        return shopRepository.findAllByTotalGreaterThan(value)
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(final Long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        return shopMapper.toDTO(shop.orElse(null));
    }

    public ShopDTO save(final ShopDTO shopDTO) {
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(ItemDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        shopDTO.setDate(LocalDateTime.now());
        shopRepository.save(shopMapper.fromDTO(shopDTO));
        return shopDTO;
    }

    public List<ShopDTO> getShopByFilter(final LocalDate dataInicio,
                                         final LocalDate dataFim,
                                         final BigDecimal valorMinimo,
                                         final Pageable pageable) {
        return shopRepository.findAll(SpecificationShopByFilters
                .builder()
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .valorMinimo(valorMinimo)
                .build(), pageable).stream().map(shopMapper::toDTO).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(final LocalDate dataInicio,
                                         final LocalDate dataFim) {
        return reportRepository.getReportByDate(dataInicio.atTime(0, 0, 0),
                dataFim.atTime(23, 59, 59));
    }
}