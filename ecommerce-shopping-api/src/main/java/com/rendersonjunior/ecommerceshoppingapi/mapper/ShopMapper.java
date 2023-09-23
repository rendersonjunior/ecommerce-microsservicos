package com.rendersonjunior.ecommerceshoppingapi.mapper;

import com.rendersonjunior.ecommerceshoppingapi.dto.ShopDTO;
import com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    Shop fromDTO(ShopDTO ShopDTO);

    ShopDTO toDTO(Shop shop);

}
