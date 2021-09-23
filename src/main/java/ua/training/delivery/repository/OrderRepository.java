package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.delivery.entity.City;
import ua.training.delivery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
