package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
