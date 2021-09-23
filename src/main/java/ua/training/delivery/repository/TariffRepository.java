package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.training.delivery.entity.Tariff;

import java.util.Optional;


public interface TariffRepository extends JpaRepository<Tariff,Long> {


    @Query("SELECT t FROM Tariff t where t.id = 1")
    Optional<Tariff> getFirst();
}
