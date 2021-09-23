package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.delivery.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {


}
