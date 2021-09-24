package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.delivery.entity.City;
import ua.training.delivery.repository.CityRepository;
import ua.training.delivery.service.CityService;

import java.util.List;


@Service
public class CityServiceImpl implements CityService {
    CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public boolean create(City city) {
        return false;
    }

    @Override
    public boolean update(City city) {
        return false;
    }

    @Override
    public float convertToDecimalDegrees(float deg, float min, float sec) {
        return 0;
    }
}
