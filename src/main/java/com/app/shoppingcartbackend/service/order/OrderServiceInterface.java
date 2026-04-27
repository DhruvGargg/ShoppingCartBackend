package com.app.shoppingcartbackend.service.order;

import com.app.shoppingcartbackend.model.Order;

public interface OrderServiceInterface {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

}
