package ua.training.delivery.service.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.delivery.entity.Order;
import ua.training.delivery.repository.OrderRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void findByIdTest() {
        Order order = new Order();
        order.setId(2L);
        doReturn(Optional.of(order)).when(orderRepository).findById(2L);
        Assert.assertEquals(Optional.of(order), orderService.findById(2));
        verify(orderRepository, times(1)).findById(2L);
    }

    @Test
    public void createTest(){


    }
}
