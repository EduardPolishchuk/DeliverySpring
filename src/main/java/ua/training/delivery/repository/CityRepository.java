package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.delivery.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
