package ua.training.delivery.service.impl;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.delivery.entity.*;
import ua.training.delivery.repository.OrderRepository;
import ua.training.delivery.repository.TariffRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private TariffRepository tariffRepository;

    private Order order;

    @MockBean
    private Pageable pageable;

    @Before
    public void init() {
        order = Order.builder()
                .id(2L)
                .parcel(Parcel.builder()
                        .height(1F)
                        .length(1F)
                        .weight(1F)
                        .width(1F).build())
                .build();
    }

    @Test
    public void findByIdTest() {
        doReturn(Optional.of(order)).when(orderRepository).findById(2L);
        Assert.assertEquals(Optional.of(order), orderService.findById(2));
        verify(orderRepository, times(1)).findById(2L);
    }

    @Test
    public void createTest() {
        orderService.create(order);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void updateTest() {
        orderService.create(order);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void getParcelTest() {
        doReturn(order).when(orderRepository).getById(2L);
        boolean isGet =  orderService.getParcel(2L);
        verify(orderRepository, times(1)).getById(2L);
        verify(orderRepository, times(1)).save(order);
        Assert.assertTrue(isGet);
        Assert.assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    public void findAllTest(){
        orderService.findAll(pageable);
        verify(orderRepository, times(1)).findAll(pageable);
    }

    @Test
    public void findUserOrdersTest(){
        User user = new User();
        orderService.findUserOrders(user,pageable);
        verify(orderRepository, times(1)).findUserOrders(user,pageable);
    }


    @Test
    public void findUserOrdersWithStatusTest(){
        User user = new User();
        orderService.findUserOrdersWithStatus(user,pageable,OrderStatus.DELIVERED);
        verify(orderRepository, times(1))
                .findUserOrdersWithStatus(user,pageable,OrderStatus.DELIVERED);
    }


    @Test
    public void findOrdersWithStatusTest(){
        orderService.findOrdersWithStatus(pageable,OrderStatus.DELIVERED);
        verify(orderRepository, times(1))
                .findAllByStatus(OrderStatus.DELIVERED,pageable);
    }

    @Test
    public void calculateOrderPriceTest(){
        Tariff tariff = Tariff.builder()
                .id(1L)
                .additional(10)
                .uahPerKilogramWeight(0.1F)
                .uahPerKilometerDistance(0.1F)
                .uahPerMillimeterLength(0.1F)
                .uahPerMillimeterWidth(0.1F)
                .uahPerMillimeterHeight(0.1F)
                .build();
        City cityFrom = City.builder().latitude(40).longitude(40).build();
        City cityTo = City.builder().latitude(50).longitude(50).build();
        order.setCityFrom(cityFrom);
        order.setCityTo(cityTo);
        doReturn(Optional.of(tariff)).when(tariffRepository).getFirst();
        BigDecimal result = orderService.calculateOrderPrice(order);
        Assert.assertEquals(BigDecimal.valueOf(146.3), result);
    }
}
