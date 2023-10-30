package com.example.inventory.service.productInventory;

import com.example.inventory.entity.Product;
import com.example.inventory.entity.ProductInventory;
import com.example.inventory.exception.NotFoundException;
import com.example.inventory.service.persistence.PersistenceService;
import com.example.inventory.service.product.ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductInventoryServiceImpl implements ProductInventoryService{

    private final PersistenceService persistenceService;
    private final ProductService productService;

    @Override
    public long getProductInventoryCount(@NotNull Product product) {
        return persistenceService.getInventoryCountOfProduct(product);
    }

    @Override
    public void addToProductInventory(@NotNull Long productId, long quantity) {
        Optional<Product> product = productService.getProductByProductId(productId);
        product.ifPresentOrElse(p->addToProductInventory(p,quantity),()-> {throw new NotFoundException("Product not found");});
    }

    @Override
    public void addToProductInventory(@NotNull Product p, long quantity){
        List<ProductInventory> productInventoryList = new ArrayList<>();
        for(int i=0;i<quantity;i++){
            ProductInventory productInventory = ProductInventory.builder().product(p).available(true).build();
            productInventoryList.add(productInventory);
        }
        persistenceService.saveProductInventory(productInventoryList);
    }

}
