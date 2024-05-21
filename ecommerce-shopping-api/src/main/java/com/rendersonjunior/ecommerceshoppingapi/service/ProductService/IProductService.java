package com.rendersonjunior.ecommerceshoppingapi.service.ProductService;

import com.rendersonjunior.dto.ProductDTO;

public interface IProductService {

    ProductDTO getProductByIdentifier(final String productIdentifier);
}
