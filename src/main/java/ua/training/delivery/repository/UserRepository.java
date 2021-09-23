package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
