package br.com.rendersonjunior.ecommerceshoppingapi.mapper;

import com.rendersonjunior.dto.ShopDTO;
import br.com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import com.rendersonjunior.dto.ShopRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    Shop fromDTO(ShopDTO shopDTO);

    ShopDTO toDTO(Shop shop);

    Shop fromRequestDTO(ShopRequestDTO shopRequestDTO);

    ShopRequestDTO toRequestDTO(Shop shop);

}
