package com.app.shoppingcartbackend.service.order;

import com.app.shoppingcartbackend.model.Order;

import java.util.List;

public interface OrderServiceInterface {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getOrders(Long userId);
}
