package ua.training.delivery.service.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.delivery.entity.User;
import ua.training.delivery.repository.UserRepository;
import ua.training.delivery.service.impl.UserServiceImpl;

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
        boolean isCreated = userService.create(user);
        Mockito.verify(userRepository,Mockito.times(1)).save(user);
        Mockito.verify(userRepository,Mockito.times(1)).existsByLogin(user.getLogin());
        Assert.assertTrue(isCreated);
    }

    @Test
    public void balanceReplenishment() {
        Assertions.assertTrue(true);
    }
}
