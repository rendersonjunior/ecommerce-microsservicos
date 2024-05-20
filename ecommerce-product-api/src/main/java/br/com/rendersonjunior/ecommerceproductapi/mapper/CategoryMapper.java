package br.com.rendersonjunior.ecommerceproductapi.mapper;

import br.com.rendersonjunior.ecommerceproductapi.model.Category;
import com.rendersonjunior.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromDTO(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category category);

}
