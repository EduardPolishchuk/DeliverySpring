package ua.training.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.delivery.entity.Parcel;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {
}
