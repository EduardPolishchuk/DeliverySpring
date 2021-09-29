package ua.training.delivery.service.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.delivery.entity.Tariff;
import ua.training.delivery.repository.TariffRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TariffServiceImplTest {

    @Autowired
    private TariffServiceImpl tariffService;

    @MockBean
    private TariffRepository tariffRepository;

    @Test
    public void getTariff() {
        Tariff tariff = new Tariff();
        doReturn(Optional.of(tariff)).when(tariffRepository).getFirst();
        Assert.assertEquals(tariff, tariffService.getTariff());
        verify(tariffRepository, times(1)).getFirst();
    }

    @Test
    public void updateTariff() {
        Tariff tariff = new Tariff();
        boolean isUpdated = tariffService.updateTariff(tariff);
        Assert.assertTrue(isUpdated);
        verify(tariffRepository, times(1)).save(tariff);
    }
}
