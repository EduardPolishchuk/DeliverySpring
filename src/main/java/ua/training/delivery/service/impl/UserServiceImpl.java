package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.User;
import ua.training.delivery.repository.OrderRepository;
import ua.training.delivery.repository.UserRepository;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> findIfValid(String login, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean create(User user) {
        if (userRepository.existsByLogin(user.getLogin()))
            return false;

        userRepository.save(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public BigDecimal getUserBalance(User user) {
        return userRepository.getUserBalance(user.getId());
    }

    @Override
    public BigDecimal balanceReplenishment(BigDecimal amount, User user) {
        return null;
    }
}
