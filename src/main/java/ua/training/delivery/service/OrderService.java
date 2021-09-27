package ua.training.delivery.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



public interface OrderService {

    Optional<Order> findById(long id);

    Order create(Order order);

    Order update(Order order);

    List<Order> findAll();

    Page<Order> findUserOrders(User user, Pageable pageable);

    List<Order> findUserOrdersWithStatus(User user, OrderStatus status);

    List<Order> findOrdersWithStatus(OrderStatus status);

    BigDecimal calculateOrderPrice(Order order);
}
