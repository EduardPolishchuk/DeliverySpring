package ua.training.delivery.service;

import ua.training.delivery.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> findAll();

        boolean create(City city);

    boolean update(City city);

    float convertToDecimalDegrees(float deg, float min, float sec);
}
