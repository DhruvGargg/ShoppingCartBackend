package com.app.shoppingcartbackend.service.order;

import com.app.shoppingcartbackend.dto.OrderDTO;
import com.app.shoppingcartbackend.model.Order;

import java.util.List;

public interface OrderServiceInterface {
    Order placeOrder(Long userId);
    OrderDTO getOrder(Long orderId);

    List<OrderDTO> getUserOrders(Long userId);
}
