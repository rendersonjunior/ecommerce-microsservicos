package br.com.rendersonjunior.ecommerceproductapi.mapper;

import br.com.rendersonjunior.ecommerceproductapi.dto.ProductDTO;
import br.com.rendersonjunior.ecommerceproductapi.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromDTO(ProductDTO productDTO);

    ProductDTO toDTO(Product product);

}
