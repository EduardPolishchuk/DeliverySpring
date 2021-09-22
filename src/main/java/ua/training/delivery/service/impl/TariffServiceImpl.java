package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.delivery.entity.Tariff;
import ua.training.delivery.repository.TariffRepository;
import ua.training.delivery.service.TariffService;

import java.util.Optional;


@Service
public class TariffServiceImpl implements TariffService {

    TariffRepository tariffRepository;

    @Autowired
    public TariffServiceImpl(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public Tariff getTariff() {
        return tariffRepository.findById(1L).orElse(new Tariff());
    }

    @Override
    public boolean updateTariff(Tariff tariff) {
        return false;
    }
}
