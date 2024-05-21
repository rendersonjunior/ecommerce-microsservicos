package com.rendersonjunior.ecommerceshoppingapi.service.product;

import com.rendersonjunior.dto.ProductDTO;

public interface IProductService {

    ProductDTO getProductByIdentifier(final String productIdentifier);
}
