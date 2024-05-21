package br.com.rendersonjunior.ecommerceproductapi.service;

import br.com.rendersonjunior.ecommerceproductapi.mapper.ProductMapper;
import br.com.rendersonjunior.ecommerceproductapi.model.Product;
import br.com.rendersonjunior.ecommerceproductapi.repository.ProductRepository;
import com.rendersonjunior.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductService(final ProductRepository productRepository,
                          final ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

    }

    public List<ProductDTO> getProductByCategoryId(final Long categoryId) {
        return productRepository.getProductByCategory(categoryId)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

    }

    public ProductDTO findByProductIdentifier(final String productIdentifier) {
        return productRepository.findByProductIdentifier(productIdentifier)
                .map(productMapper::toDTO)
                .orElse(null);

    }

    public ProductDTO save(ProductDTO productDTO) {
        return productMapper.toDTO(productRepository.save(productMapper.fromDTO(productDTO)));

    }

    public ProductDTO delete(final Long productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(("Not have record to delete")));
        productRepository.delete(product);
        return productMapper.toDTO(product);

    }

    public ProductDTO editProduct(final long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        if (!productDTO.getNome().isBlank()) {
            product.setNome(productDTO.getNome());
        }

        if (Objects.nonNull(productDTO.getPreco())) {
            product.setPreco(productDTO.getPreco());
        }

        return productMapper.toDTO(productRepository.save(product));

    }

    public Page<ProductDTO> getAllPage(final Pageable page) {
        return productRepository.findAll(page).map(productMapper::toDTO);

    }

}

