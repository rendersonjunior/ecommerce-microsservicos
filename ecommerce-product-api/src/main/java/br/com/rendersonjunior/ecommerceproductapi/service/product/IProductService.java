package br.com.rendersonjunior.ecommerceproductapi.service.product;

import com.rendersonjunior.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getAll();

    List<ProductDTO> getProductByCategoryId(final Long categoryId);

    ProductDTO findByProductIdentifier(final String productIdentifier);

    ProductDTO save(ProductDTO productDTO);

    ProductDTO delete(final Long productId);

    ProductDTO editProduct(final long id, final ProductDTO productDTO);

    Page<ProductDTO> getAllPage(final Pageable page);

}
