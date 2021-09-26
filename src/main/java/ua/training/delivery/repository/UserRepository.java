package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false " +
            "END FROM User u WHERE u.login = :login")
    boolean existsByLogin(@Param("login") String login);


    @Query("SELECT balance FROM User where id=?1")
    BigDecimal getUserBalance(Long userId);

    @Modifying
    @Query("UPDATE  User u set u.balance=u.balance+?2 where u.id=?1")
    void changeUserBalance(Long userId, BigDecimal amount);
}
