package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.User;
import ua.training.delivery.repository.OrderRepository;
import ua.training.delivery.service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class ReceiptServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public ReceiptServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Order order) {
        return false;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findUserOrders(User user) {
        return null;
    }

    @Override
    public List<Order> findUserOrdersWithStatus(User user, OrderStatus status) {
        return null;
    }

    @Override
    public List<Order> findOrdersWithStatus(OrderStatus status) {
        return null;
    }

    @Override
    public BigDecimal calculateOrderPrice(Order order) {
        return null;
    }
}
