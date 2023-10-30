package com.example.inventory.persistence.repository;

import com.example.inventory.entity.Order;
import com.example.inventory.entity.Product;
import com.example.inventory.entity.ProductInventory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    @QueryHints({@QueryHint(name="jakarta.persistence.query.timeout", value="1000")})
    Page<ProductInventory> findByProductEqualsAndAvailableIsTrueOrAvailableIsFalseAndUnavailableTillIsLessThanAndOrder_PaymentIdIsNull(Product product, LocalDateTime unavailableTill, Pageable pageable);

    long countByProductEqualsAndOrderIsNullOrOrderIsNotNullAndOrder_PaymentIdIsNull(Product product);

    List<ProductInventory> findByOrderEquals(Order order);




}
