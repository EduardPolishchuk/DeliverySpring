package ua.training.delivery.service.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.delivery.entity.City;
import ua.training.delivery.repository.CityRepository;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceImplTest {

    @Autowired
    private CityServiceImpl cityService;

    @MockBean
    private CityRepository cityRepository;

    @Test
    public void findAllTest() {
        cityService.findAll();
        verify(cityRepository, times(1)).findAll();
    }

    @Test
    public void createTest() {
        City city = new City();
        boolean isCreated = cityService.create(city);
        Assert.assertTrue(isCreated);
        verify(cityRepository, times(1)).save(city);
    }


    @Test
    public void updateTest() {
        City city = new City();
        boolean isCreated = cityService.create(city);
        Assert.assertTrue(isCreated);
        verify(cityRepository, times(1)).save(city);
    }

    @Test
    public void convertToDecimalDegrees() {
        Assert.assertTrue(
                Math.abs(10.169F - cityService.convertToDecimalDegrees(10, 10, 10)) < 0.1);
    }
}
