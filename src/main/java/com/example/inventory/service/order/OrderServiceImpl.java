package com.example.inventory.service.order;

import com.example.inventory.entity.Order;
import com.example.inventory.entity.Product;
import com.example.inventory.exception.NotFoundException;
import com.example.inventory.fsd.OrderProduct;
import com.example.inventory.fsd.OrderRequest;
import com.example.inventory.fsd.PreparedOrderProduct;
import com.example.inventory.service.persistence.PersistenceService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final PersistenceService persistenceService;

    private final ProcessOrderService processOrderService;

    @Override
    public Long createOrder(@NotNull OrderRequest orderRequest) {
        List<PreparedOrderProduct> orders = validateOrderRequest(orderRequest);
        Order order = Order.builder().orderDate(LocalDateTime.now()).build();
        persistenceService.createOrder(order);
        blockProductInventory(orders,order);
        return order.getOrderId();
    }

    private void blockProductInventory(@NotNull List<PreparedOrderProduct> orderProducts,@NotNull Order order){
        for(PreparedOrderProduct orderProduct : orderProducts){
            processOrderService.processOrder(orderProduct,order);
        }
    }

    private List<PreparedOrderProduct> validateOrderRequest(@NotNull OrderRequest orderRequest){
        List<OrderProduct> orderProducts  = orderRequest.getOrderProductList();
        List<PreparedOrderProduct> preparedOrderProductList = new ArrayList<>();
        for(OrderProduct orderProduct : orderProducts){
            Optional<Product> productOptional = persistenceService.getProductByProductId(orderProduct.getProductId());
            Product product = productOptional.orElseThrow(()->new NotFoundException("Product not found"));
            long inventoryCount = persistenceService.getInventoryCountOfProduct(product);
            if(inventoryCount<orderProduct.getQuantity()){
                throw new NotFoundException("Quantity not available");
            }
            preparedOrderProductList.add(PreparedOrderProduct.builder().
                    product(product).
                    quantity(orderProduct.getQuantity()).
                    build());
        }
        return preparedOrderProductList;

    }

    @Override
    public void confirmOrder(@NotNull Long orderId,@NotNull String paymentID){
        Optional<Order> orderOptional = persistenceService.getOrderByOrderId(orderId);
        Order order = orderOptional.orElseThrow(()->new NotFoundException("Order not found"));
        order.setPaymentId(paymentID);
        persistenceService.updateOrder(order);

    }

}
