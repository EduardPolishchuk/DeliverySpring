package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.delivery.entity.*;
import ua.training.delivery.repository.OrderRepository;
import ua.training.delivery.repository.TariffRepository;
import ua.training.delivery.service.OrderService;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TariffRepository tariffRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, TariffRepository tariffRepository) {
        this.orderRepository = orderRepository;
        this.tariffRepository = tariffRepository;
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findUserOrders(User user) {
        return orderRepository.findUserOrders(user.getId());
    }

    @Override
    public List<Order> findUserOrdersWithStatus(User user, OrderStatus status) {
        return orderRepository.findUserOrdersWithStatus(user.getId(), status.toString());
    }

    @Override
    public List<Order> findOrdersWithStatus(OrderStatus status) {
        return orderRepository.findOrdersWithStatus(status.toString());
    }

    @Override
    public BigDecimal calculateOrderPrice(Order order) {
        Tariff tariff = tariffRepository.getFirst().orElseThrow(EntityNotFoundException::new);
        Parcel parcel = order.getParcel();
        return BigDecimal.valueOf(tariff.getUahPerKilogramWeight() * parcel.getWeight()
                + tariff.getUahPerMillimeterHeight() * parcel.getHeight()
                + tariff.getUahPerMillimeterLength() * parcel.getLength()
                + tariff.getUahPerMillimeterWidth() * parcel.getWidth()
                + tariff.getUahPerKilogramWeight() * getDistance(order.getCityFrom(), order.getCityTo())
                + tariff.getAdditional()).setScale(1,BigDecimal.ROUND_HALF_UP);
    }

    private float getDistance(City cityFrom, City cityTo) {
        float lat1 = cityFrom.getLatitude();
        float lat2 = cityTo.getLatitude();
        float lng1 = cityFrom.getLongitude();
        float lng2 = cityTo.getLongitude();

        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (earthRadius * c) / 1000;
    }
}
