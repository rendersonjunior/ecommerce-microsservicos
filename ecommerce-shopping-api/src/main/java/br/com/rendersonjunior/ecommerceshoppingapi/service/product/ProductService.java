package br.com.rendersonjunior.ecommerceshoppingapi.service.product;

import com.rendersonjunior.dto.ProductDTO;
import com.rendersonjunior.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProductService implements IProductService {

    private final String productApiURL = "http://localhost:8081";

    @Override
    public ProductDTO getProductByIdentifier(final String productIdentifier) {
        try {
            final var webClient = WebClient.builder()
                    .baseUrl(productApiURL)
                    .build();

            final var product = webClient.get()
                    .uri("/product/".concat(productIdentifier))
                    .retrieve()
                    .bodyToMono(ProductDTO.class);

            return product.block();
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
    }

}