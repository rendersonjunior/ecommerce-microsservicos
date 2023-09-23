package com.rendersonjunior.ecommerceshoppingapi.service;

import com.rendersonjunior.ecommerceshoppingapi.dto.ShopDTO;
import com.rendersonjunior.ecommerceshoppingapi.mapper.ShopMapper;
import com.rendersonjunior.ecommerceshoppingapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    //Continuar inserção de métodos

    public ShopDTO save(ShopDTO shopDTO) {
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce(0.0, Double::sum));

        shopRepository.save(shopMapper.fromDTO(shopDTO));
        return shopDTO;
    }

}
