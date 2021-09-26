package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.training.delivery.entity.OrderStatus;
import ua.training.delivery.entity.Receipt;
import ua.training.delivery.entity.User;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    @Query("select r FROM Receipt r left join Order o on r.order = o " +
            "where o.userSender =?1 and r.paid =?2")
    List<Receipt> findUserReceipts(User user, boolean isPaid);



    @Modifying
    @Query("UPDATE Receipt r  set r.order.status=?3, " +
            "r.order.userSender.balance= r.order.userSender.balance-r.price, r.paid=1 " +
            "where r.order.userSender=?1 and r.id=?2")
    void  userPaysReceipt(User user, Long receiptId, OrderStatus status);


}
