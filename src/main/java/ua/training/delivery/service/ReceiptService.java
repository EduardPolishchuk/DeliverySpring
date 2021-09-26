package ua.training.delivery.service;


import ua.training.delivery.entity.Receipt;
import ua.training.delivery.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {

    Optional<Receipt> findById(long id);

    boolean create(Long orderID);

    Receipt update(Receipt receipt);

    List<Receipt> findUserReceipts(User user, boolean paid);

    boolean userPaysReceipt(User user, Long receiptId);

    List<Receipt> findAll();
}
