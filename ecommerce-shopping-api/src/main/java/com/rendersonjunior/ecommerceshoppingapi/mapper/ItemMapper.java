package com.rendersonjunior.ecommerceshoppingapi.mapper;

import com.rendersonjunior.dto.ItemDTO;
import com.rendersonjunior.ecommerceshoppingapi.model.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item fromDTO(ItemDTO itemDTO);

    ItemDTO toDTO(Item item);

}
