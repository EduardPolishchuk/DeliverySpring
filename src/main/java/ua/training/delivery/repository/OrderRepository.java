package ua.training.delivery.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.training.delivery.entity.City;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o where o.userSender = :user")
    Page<Order> findUserOrders(@Param("user") User user, Pageable pageable);

    @Query("SELECT o FROM Order o where o.userSender = :user and  o.status = :status")
    Page<Order> findUserOrdersWithStatus(@Param("user") User user,
                                         Pageable pageable, @Param("status") OrderStatus status);

    @Query("SELECT o FROM Order o where   o.status = :status")
    List<Order> findOrdersWithStatus(@Param("status") String status);


}
