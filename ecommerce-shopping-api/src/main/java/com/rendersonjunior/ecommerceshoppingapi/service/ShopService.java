package com.rendersonjunior.ecommerceshoppingapi.service;

import com.rendersonjunior.ecommerceshoppingapi.dto.ItemDTO;
import com.rendersonjunior.ecommerceshoppingapi.dto.ShopDTO;
import com.rendersonjunior.ecommerceshoppingapi.mapper.ShopMapper;
import com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import com.rendersonjunior.ecommerceshoppingapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    private final ShopMapper shopMapper;

    public List<ShopDTO> getAll() {
        return shopRepository.findAll()
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        return shopRepository.findAllByUserIdentifier(userIdentifier)
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        return shopRepository.findAllByDateGreaterThan(shopDTO.getDate())
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByValue(BigDecimal value) {
        return shopRepository.findAllByTotalGreaterThan(value)
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(Long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        return shopMapper.toDTO(shop.orElse(null));
    }

    public ShopDTO save(ShopDTO shopDTO) {
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(ItemDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        shopDTO.setDate(LocalDateTime.now());
        shopRepository.save(shopMapper.fromDTO(shopDTO));
        return shopDTO;
    }

}