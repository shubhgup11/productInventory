package com.example.inventory.service.product;

import com.example.inventory.entity.Product;
import com.example.inventory.fsd.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    ProductResponse getAllProducts(Pageable pageable);

    Optional<Product> getProductByProductId(Long productId);

    Long createProduct(com.example.inventory.fsd.Product product);
}
