package ua.training.delivery.service;

import org.springframework.stereotype.Service;
import ua.training.delivery.entity.Tariff;



public interface TariffService {

    Tariff getTariff();

    boolean updateTariff(Tariff tariff);
}
