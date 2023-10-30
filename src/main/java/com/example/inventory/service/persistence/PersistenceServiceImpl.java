package com.example.inventory.service.persistence;


import com.example.inventory.entity.Order;
import com.example.inventory.entity.Product;
import com.example.inventory.entity.ProductInventory;
import com.example.inventory.persistence.repository.OrderRepository;
import com.example.inventory.persistence.repository.ProductInventoryRepository;
import com.example.inventory.persistence.repository.ProductRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PersistenceServiceImpl implements PersistenceService{

    private final ProductRepository productRepository;

    private final ProductInventoryRepository productInventoryRepository;

    private final OrderRepository orderRepository;

    @Override
    public Page<Product> getAllProducts(@NotNull Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<ProductInventory> getFirstNProductsOfProduct(@NotNull Product p,int n) {
       return productInventoryRepository.findByProductEqualsAndAvailableIsTrueOrAvailableIsFalseAndUnavailableTillIsLessThanAndOrder_PaymentIdIsNull(p,LocalDateTime.now(), PageRequest.of(0,n)).toList();
    }

    @Override
    @Cacheable(cacheNames = "productInventoryCache") // evict cache on countMismatch Exception and also in scheduled manner
    public long getInventoryCountOfProduct(@NotNull Product product) {
        return productInventoryRepository.countByProductEqualsAndOrderIsNullOrOrderIsNotNullAndOrder_PaymentIdIsNull(product);
    }

    @Override
    public Optional<Product> getProductByProductId(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void saveProductInventory(@NotNull List<ProductInventory> productInventories) {
        productInventoryRepository.saveAll(productInventories);
    }

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderByOrderId(@NotNull Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void updateOrder(@NotNull Order order){
        orderRepository.save(order);
    }

    @Override
    public Long createProduct(@NotNull Product product) {
       productRepository.save(product);
       return product.getProductId();
    }


}
