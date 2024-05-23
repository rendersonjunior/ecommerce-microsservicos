package br.com.rendersonjunior.ecommerceproductapi.controller;

import br.com.rendersonjunior.ecommerceproductapi.service.product.IProductService;
import com.rendersonjunior.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

    @GetMapping("/{productIdentifier}")
    public ProductDTO findById(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO userDTO) {
        return productService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    public ProductDTO delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PostMapping("/{id}")
    public ProductDTO editProduct(@PathVariable Long id,
                                  @RequestBody ProductDTO productDTO) {
        return productService.editProduct(id, productDTO);
    }

    @GetMapping("/pageable")
    public Page<ProductDTO> getProductsPage(Pageable pageable) {
        return productService.getAllPage(pageable);
    }

}
