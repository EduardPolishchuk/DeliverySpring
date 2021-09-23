package ua.training.delivery.service;


import ua.training.delivery.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findIfValid(String login, String password);
    Optional<User> findById(long id);
    boolean create (User user);
    boolean update(User user);
    List<User> findAll();
    BigDecimal getUserBalance(User user);
    BigDecimal balanceReplenishment(BigDecimal amount, User user);
}
