package com.example.inventory.service.order;

import com.example.inventory.entity.Order;
import com.example.inventory.entity.Product;
import com.example.inventory.entity.ProductInventory;
import com.example.inventory.exception.InventoryCountMismatchException;
import com.example.inventory.fsd.OrderProduct;
import com.example.inventory.fsd.PreparedOrderProduct;
import com.example.inventory.service.persistence.PersistenceService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessOrderService {

    private final PersistenceService persistenceService;

    @Transactional
    public void processOrder(@NotNull PreparedOrderProduct orderProduct,@NotNull Order order){
        List<ProductInventory> productInventoryList = persistenceService.getFirstNProductsOfProduct(orderProduct.getProduct(),orderProduct.getQuantity());
        if(productInventoryList.size()< orderProduct.getQuantity()){
            throw new InventoryCountMismatchException("quantity not available");
        }
        for(ProductInventory productInventory : productInventoryList){
            productInventory.setOrder(order);
            productInventory.setAvailable(false);
            productInventory.setUnavailableTill(LocalDateTime.now().plusMinutes(2));
        }

        persistenceService.saveProductInventory(productInventoryList);
    }
}
