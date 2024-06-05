package br.com.rendersonjunior.ecommerceproductapi.service;

import br.com.rendersonjunior.ecommerceproductapi.mapper.ProductMapper;
import br.com.rendersonjunior.ecommerceproductapi.repository.ProductRepository;
import br.com.rendersonjunior.ecommerceproductapi.service.product.ProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

}
