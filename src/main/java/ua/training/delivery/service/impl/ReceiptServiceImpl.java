package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.delivery.entity.Order;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.Receipt;
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


    @Override
    public Optional<Receipt> findById(long id) {
        return receiptRepository.findById(id);
    }

    @Override
    public Receipt create(Receipt receipt) {
        return receiptRepository.save(receipt);
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
    public boolean userPaysReceipt(User user, Receipt receipt) {
        receiptRepository.userPaysReceipt(user,receipt, OrderStatus.WAITING_FOR_PAYMENT);
        return true;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }
}
