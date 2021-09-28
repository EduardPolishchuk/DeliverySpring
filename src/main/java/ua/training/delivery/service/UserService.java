package ua.training.delivery.service;


import ua.training.delivery.entity.Role;
import ua.training.delivery.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByLogin(String login);
    boolean create (User user);
    boolean update(User user);
    List<User> findAllByRole(Role role);
    boolean balanceReplenishment(User user,BigDecimal amount );
}
