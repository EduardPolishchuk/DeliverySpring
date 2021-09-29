package ua.training.delivery.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.Receipt;
import ua.training.delivery.entity.User;
import ua.training.delivery.repository.ReceiptRepository;
import ua.training.delivery.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptServiceImplTest {

    @Autowired
    private ReceiptServiceImpl receiptService;

    @MockBean
    private ReceiptRepository receiptRepository;

    @MockBean
    private OrderService orderService;


    @Test
    public void createTest() {
        Order order = Order.builder().id(2L).build();
        doReturn(Optional.of(order)).when(orderService).findById(2L);
        when(orderService.calculateOrderPrice(order)).thenReturn(BigDecimal.valueOf(200));
        boolean isCreated = receiptService.create(2L);
        verify(orderService, times(1)).calculateOrderPrice(order);
        Assert.assertEquals(OrderStatus.WAITING_FOR_PAYMENT, order.getStatus());
    }

    @Test
    public void updateTest() {
        Receipt receipt = new Receipt();
        receiptService.update(receipt);
        verify(receiptRepository, times(1)).save(receipt);
    }

    @Test
    public void findUserReceiptsTest() {
        User user = new User();
        receiptService.findUserReceipts(user, false);
        verify(receiptRepository, times(1)).findUserReceipts(user, false);


    }

    @Test
    public void userPaysReceipt() {
        User user = new User();
        Receipt receipt = Receipt.builder()
                .order(Order.builder()
                        .userSender(User.builder().balance(BigDecimal.valueOf(300)).build())
                        .build())
                .price(BigDecimal.valueOf(100))
                .build();
        when(receiptRepository.findById(2L)).thenReturn(Optional.of(receipt));
        boolean isSuccessful = receiptService.userPaysReceipt(user, 2L);
        verify(receiptRepository, times(1)).findById(2L);
        verify(receiptRepository, times(1)).save(receipt);
        Assert.assertTrue(isSuccessful);
        Assert.assertTrue(receipt.isPaid());
        Assert.assertEquals(BigDecimal.valueOf(200), receipt.getOrder().getUserSender().getBalance());
        Assert.assertEquals(OrderStatus.PARCEL_DELIVERY, receipt.getOrder().getStatus());
        Assert.assertEquals(LocalDate.now().plusDays(2), receipt.getOrder().getReceivingDate());
    }
}
