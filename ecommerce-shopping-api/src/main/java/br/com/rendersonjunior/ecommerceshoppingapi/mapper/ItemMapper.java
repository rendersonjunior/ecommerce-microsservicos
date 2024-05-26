package br.com.rendersonjunior.ecommerceshoppingapi.mapper;

import br.com.rendersonjunior.ecommerceshoppingapi.model.Item;
import com.rendersonjunior.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item fromDTO(ItemDTO itemDTO);

    ItemDTO toDTO(Item item);

}
