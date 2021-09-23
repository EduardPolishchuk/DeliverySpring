package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.User;
import ua.training.delivery.repository.OrderRepository;
import ua.training.delivery.repository.ReceiptRepository;
import ua.training.delivery.service.OrderService;
import ua.training.delivery.service.ReceiptService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }




}
