package com.example.inventory.service.persistence;

import com.example.inventory.entity.Order;
import com.example.inventory.entity.Product;
import com.example.inventory.entity.ProductInventory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersistenceService {

    Page<Product> getAllProducts(Pageable pageable);

    List<ProductInventory> getFirstNProductsOfProduct(Product p,int n);


    long getInventoryCountOfProduct(Product product);

    Optional<Product> getProductByProductId(long productId);

    void saveProductInventory(List<ProductInventory> productInventories);

    void createOrder(Order order);

    Optional<Order> getOrderByOrderId(Long orderId);

    void updateOrder(Order order);

    Long createProduct(Product product);

}
