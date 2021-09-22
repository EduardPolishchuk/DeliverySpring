package ua.training.delivery.service;

import ua.training.delivery.entity.Tariff;

import java.util.Optional;

public interface TariffService {

    Optional<Tariff> getTariff();

    boolean updateTariff(Tariff tariff);
}
