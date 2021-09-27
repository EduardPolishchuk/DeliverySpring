package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.Receipt;
import ua.training.delivery.entity.User;
import ua.training.delivery.repository.ReceiptRepository;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.ReceiptService;
import ua.training.delivery.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    private final OrderService orderService;

    private final UserService userService;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository,
                              OrderService orderService, UserService userService) {
        this.receiptRepository = receiptRepository;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public Optional<Receipt> findById(long id) {
        return receiptRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean create(Long orderID) {
        Order order = orderService.findById(orderID).orElseThrow(IndexOutOfBoundsException::new);
        Receipt receipt = Receipt.builder()
                .order(order)
                .paid(false)
                .price(orderService.calculateOrderPrice(order))
                .build();
        receiptRepository.save(receipt);
        order.setStatus(OrderStatus.WAITING_FOR_PAYMENT);
        orderService.update(order);
        return true;
    }

    @Override
    public Receipt update(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public List<Receipt> findUserReceipts(User user, boolean paid) {
        return receiptRepository.findUserReceipts(user, paid);
    }

    @Override
    @Transactional
    public boolean userPaysReceipt(User user,Long receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).get();
        Order order = receipt.getOrder();
        User userSender = order.getUserSender();
        if(receipt.getPrice().doubleValue() > userSender.getBalance().doubleValue()){
            return false;
        }
        order.setStatus(OrderStatus.PARCEL_DELIVERY);
        order.setReceivingDate(LocalDate.now().plusDays(2));
        receipt.setPaid(true);
        userSender.setBalance(userSender.getBalance().subtract(receipt.getPrice()));
        receiptRepository.save(receipt);
        user.setBalance(userSender.getBalance());
        return true;
    }


}
