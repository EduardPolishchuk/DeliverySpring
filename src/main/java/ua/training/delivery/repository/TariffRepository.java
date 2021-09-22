package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.delivery.entity.Tariff;

public interface TariffRepository extends JpaRepository<Tariff,Long> {

}
