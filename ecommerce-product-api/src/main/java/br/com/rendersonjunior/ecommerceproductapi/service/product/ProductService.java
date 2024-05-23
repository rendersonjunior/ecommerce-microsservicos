package br.com.rendersonjunior.ecommerceproductapi.service.product;

import br.com.rendersonjunior.ecommerceproductapi.mapper.ProductMapper;
import br.com.rendersonjunior.ecommerceproductapi.model.Product;
import br.com.rendersonjunior.ecommerceproductapi.repository.ProductRepository;
import br.com.rendersonjunior.ecommerceproductapi.service.category.ICategoryService;
import com.rendersonjunior.dto.ProductDTO;
import com.rendersonjunior.exception.CategoryNotFoundException;
import com.rendersonjunior.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final ICategoryService categoryService;

    public ProductService(final ProductRepository productRepository,
                          final ProductMapper productMapper, ICategoryService categoryService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
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
                .orElseThrow(ProductNotFoundException::new);
    }

    public ProductDTO save(ProductDTO productDTO) {
        if (isFalse(categoryService.existsById(productDTO.getCategory().getId()))) {
            throw new CategoryNotFoundException();
        }

        return productMapper.toDTO(productRepository.save(productMapper.fromDTO(productDTO)));
    }

    public ProductDTO delete(final Long productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);

        return productMapper.toDTO(product);
    }

    public ProductDTO editProduct(final long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        if (!productDTO.getNome().isBlank()) {
            product.setNome(productDTO.getNome());
        }
        if (nonNull(productDTO.getPreco())) {
            product.setPreco(productDTO.getPreco());
        }

        return productMapper.toDTO(productRepository.save(product));
    }

    public Page<ProductDTO> getAllPage(final Pageable page) {
        return productRepository.findAll(page).map(productMapper::toDTO);
    }

}

