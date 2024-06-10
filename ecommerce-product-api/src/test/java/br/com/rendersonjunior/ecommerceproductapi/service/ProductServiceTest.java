package br.com.rendersonjunior.ecommerceproductapi.service;

import br.com.rendersonjunior.ecommerceproductapi.mapper.ProductMapper;
import br.com.rendersonjunior.ecommerceproductapi.model.Product;
import br.com.rendersonjunior.ecommerceproductapi.repository.ProductRepository;
import br.com.rendersonjunior.ecommerceproductapi.service.product.ProductService;
import com.rendersonjunior.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void testListAllProducts() {
        final List<Product> products = new ArrayList<>();
        products.add(getProduct(1L, "prod", "prod teste", BigDecimal.ONE));
        products.add(getProduct(2L, "prod 2", "prod teste 2", BigDecimal.TEN));

        Mockito.when(productRepository.findAll()).thenReturn(products);

        final List<ProductDTO> productsReturn = productService.getAll();

        Assertions.assertEquals(2, productsReturn.size());
    }

    public static Product getProduct(final Long id,
                                     final String nome,
                                     final String descricao,
                                     final BigDecimal preco) {
        return Product.builder()
                .id(id)
                .nome(nome)
                .descricao(descricao)
                .preco(preco)
                .build();
    }
}
