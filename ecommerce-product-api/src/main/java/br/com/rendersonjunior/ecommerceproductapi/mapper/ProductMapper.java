package br.com.rendersonjunior.ecommerceproductapi.mapper;

import br.com.rendersonjunior.ecommerceproductapi.model.Product;
import com.rendersonjunior.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromDTO(ProductDTO productDTO);

    ProductDTO toDTO(Product product);

}
