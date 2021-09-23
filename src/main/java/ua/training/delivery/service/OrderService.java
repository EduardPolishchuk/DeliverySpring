package ua.training.delivery.service;

import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



public interface OrderService {

    Optional<Order> findById(long id);

    boolean create(Order order);

    boolean update(Order order);

    List<Order> findAll();

    List<Order> findUserOrders(User user);

    List<Order> findUserOrdersWithStatus(User user, OrderStatus status);

    List<Order> findOrdersWithStatus(OrderStatus status);

    BigDecimal calculateOrderPrice(Order order);
}
