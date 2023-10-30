package com.example.inventory.service.order;

import com.example.inventory.fsd.OrderRequest;

public interface OrderService {

    Long createOrder(OrderRequest orderRequest);

    void confirmOrder(Long orderId, String paymentID);

}
