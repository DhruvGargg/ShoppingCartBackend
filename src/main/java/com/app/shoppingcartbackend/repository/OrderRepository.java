package com.app.shoppingcartbackend.repository;

import com.app.shoppingcartbackend.model.Order;
import com.app.shoppingcartbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> user(User user);

    List<Order> findByUserId(Long userId);
}
