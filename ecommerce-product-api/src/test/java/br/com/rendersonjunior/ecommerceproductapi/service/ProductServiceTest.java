package br.com.rendersonjunior.ecommerceproductapi.service;

import br.com.rendersonjunior.ecommerceproductapi.mapper.ProductMapper;
import br.com.rendersonjunior.ecommerceproductapi.model.Category;
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
        final var category = getCategory(1L, "Category");
        products.add(getProduct(1L, "product", "prod", "prod teste", BigDecimal.ONE, category));
        products.add(getProduct(2L, "product-2", "prod 2", "prod teste 2", BigDecimal.TEN, category));

        Mockito.when(productRepository.findAll()).thenReturn(products);

        final List<ProductDTO> productsReturn = productService.getAll();

        Assertions.assertEquals(2, productsReturn.size());
    }

    @Test
    public void testSaveProduct() {
        final var category = getCategory(1L, "Category");
        final var productDB = getProduct(1L, "p01", "p1", "p-1", BigDecimal.TEN, category);
        final var productDTO = mapper.toDTO(getProduct(1L, "p01", "p1", "p-1", BigDecimal.TEN, category));

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(productDB);

        final var product = productService.save(productDTO);

        Assertions.assertEquals(productDTO.getNome(), product.getNome());
        Assertions.assertEquals(productDTO.getProductIdentifier(), product.getProductIdentifier());
    }

    public static Category getCategory(final Long id,
                                       final String nome) {
        return Category.builder().nome(nome).build();
    }

    public static Product getProduct(final Long id,
                                     final String productIdentifier,
                                     final String nome,
                                     final String descricao,
                                     final BigDecimal preco,
                                     final Category category) {
        return Product.builder()
                .id(id)
                .productIdentifier(productIdentifier)
                .nome(nome)
                .descricao(descricao)
                .preco(preco)
                .category(category)
                .build();
    }

}
