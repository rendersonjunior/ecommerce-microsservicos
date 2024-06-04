package br.com.rendersonjunior.ecommerceshoppingapi.service;

import br.com.rendersonjunior.ecommerceshoppingapi.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendersonjunior.dto.ProductDTO;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    public static MockWebServer mockBackEnd;
    public static String url = "http://localhost:%s";

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        final var baseUrl = String.format(url, mockBackEnd.getPort());
        ReflectionTestUtils.setField(productService, "productApiURL", baseUrl);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void test_getProductByIdentifier() throws Exception {
        final var mockProductDTO = ProductDTO.builder()
                .preco(BigDecimal.valueOf(1000))
                .productIdentifier("prod-identifier")
                .build();

        final var objectMapper = new ObjectMapper();

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockProductDTO))
                .addHeader("Content-Type", "application/json"));

        final var productDTO = productService.getProductByIdentifier("prod-identifier");

        Assertions.assertEquals(mockProductDTO.getPreco(), productDTO.getPreco());
        Assertions.assertEquals(mockProductDTO.getProductIdentifier(), productDTO.getProductIdentifier());
    }

}
