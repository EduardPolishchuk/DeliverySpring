package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.User;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o where o.userSender = :user")
    List<Order> findUserOrders(@Param("user") User user);

    @Query("SELECT o FROM Order o where o.userSender = :id and  o.status = :status")
    List<Order> findUserOrdersWithStatus(@Param("id") Long userId, @Param("status") String status);

    @Query("SELECT o FROM Order o where   o.status = :status")
    List<Order> findOrdersWithStatus(@Param("status") String status);


//    List<Order> findAll(Pageable pageable);

}
