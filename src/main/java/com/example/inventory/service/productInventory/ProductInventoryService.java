package com.example.inventory.service.productInventory;

import com.example.inventory.entity.Product;

public interface ProductInventoryService {

    long getProductInventoryCount(Product product);

    void addToProductInventory(Long productId,long quantity);

    void addToProductInventory(Product p, long quantity);
}
