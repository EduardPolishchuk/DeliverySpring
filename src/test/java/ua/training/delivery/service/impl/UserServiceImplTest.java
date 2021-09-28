package ua.training.delivery.service.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.delivery.entity.Role;
import ua.training.delivery.entity.User;
import ua.training.delivery.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void createTest() {
        User user = new User();
        user.setLogin("Login");
        user.setLogin("email@email.ua");
        boolean isCreated = userService.create(user);
        Assert.assertTrue(isCreated);
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1))
                .existsByLoginAndEmail(user.getLogin(), user.getEmail());
    }

    @Test
    public void createFailTest() {
        User user = new User();
        user.setLogin("Login");
        user.setEmail("email@email.ua");
        Mockito.doReturn(true)
                .when(userRepository)
                .existsByLoginAndEmail("Login", "email@email.ua");
        boolean isCreated = userService.create(user);
        verify(userRepository, times(0)).save(user);
        verify(userRepository, times(1))
                .existsByLoginAndEmail(user.getLogin(), user.getEmail());

        Assert.assertFalse(isCreated);

    }


    @Test
    public void balanceReplenishmentTest() {
        User user = new User();
        BigDecimal amount = BigDecimal.valueOf(200);
        user.setId(2L);
        user.setBalance(BigDecimal.valueOf(500));
        boolean isChanged = userService.balanceReplenishment(user, amount);
        Assert.assertTrue(isChanged);
        verify(userRepository, times(1)).changeUserBalance(2L, amount);
        verify(userRepository, times(1)).getUserBalance(2L);
    }

    @Test
    public void updateTest() {
        User user = new User();
        boolean isUpdated = userService.update(user);
        Assert.assertTrue(isUpdated);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void findByLoginTest() {
        User user = new User();
        doReturn(Optional.of(user)).when(userRepository).findByLogin("Login");
        Optional<User> userGet = userService.findByLogin("Login");
        Assert.assertEquals(Optional.of(user), userGet);
        verify(userRepository, times(1)).findByLogin("Login");
    }

    @Test
    public void findAllByRoleTest() {
        userService.findAllByRole(Role.USER);
        verify(userRepository, times(1)).findAllByRole(Role.USER);
    }
}
