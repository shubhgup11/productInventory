package com.example.inventory.service.product;

import com.example.inventory.entity.Product;
import com.example.inventory.entity.ProductInventory;
import com.example.inventory.exception.ProductAlreadyPresentException;
import com.example.inventory.fsd.ProductResponse;
import com.example.inventory.service.persistence.PersistenceService;
import com.example.inventory.service.productInventory.ProductInventoryService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final PersistenceService persistenceService;
    private final ProductInventoryService productInventoryService;

    @Override
    public ProductResponse getAllProducts(Pageable pageable) {
        Page<Product> products = persistenceService.getAllProducts(pageable);
        int currentPage = products.getNumber();
        int totalPage = products.getTotalPages();

        List<com.example.inventory.fsd.Product> productList = products.stream().map(this::mapProductToFsd).toList();

        return ProductResponse.builder().
                products(productList).
                currentPage(currentPage).
                totalPage(totalPage).
                build();
    }

    @Override
    public Optional<Product> getProductByProductId(@NotNull Long productId) {
        return persistenceService.getProductByProductId(productId);
    }

    @Override
    @Transactional
    public Long createProduct(@NotNull com.example.inventory.fsd.Product product) {
        Product newProduct = Product.builder().
                productTypeId(product.getProductTypeId()).
                productTypeCode(product.getProductTypeCode()).build();

        persistenceService.createProduct(newProduct);

        productInventoryService.addToProductInventory(newProduct,product.getInventoryCount());

        return newProduct.getProductId();
    }

    private com.example.inventory.fsd.Product mapProductToFsd(@NotNull Product p){
        long inventoryCount = persistenceService.getInventoryCountOfProduct(p);
        return com.example.inventory.fsd.Product.builder().
                productId(p.getProductId()).
                productTypeId(p.getProductTypeId()).
                productTypeCode(p.getProductTypeCode()).
                inventoryCount(inventoryCount).
                build();

    }
}
