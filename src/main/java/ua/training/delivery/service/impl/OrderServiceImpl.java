package ua.training.delivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.delivery.entity.*;
import ua.training.delivery.repository.OrderRepository;
import ua.training.delivery.repository.TariffRepository;
import ua.training.delivery.service.OrderService;
import static ua.training.delivery.constants.Constants.EARTH_RADIUS;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
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
    @Transactional
    public boolean getParcel(Long orderId) {
        Order order = orderRepository.getById(orderId);
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
        return true;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findUserOrders(User user, Pageable pageable) {

        return orderRepository.findUserOrders(user, pageable);
    }

    @Override
    public Page<Order> findUserOrdersWithStatus(User user, Pageable pageable, OrderStatus status) {
        return orderRepository.findUserOrdersWithStatus(user,pageable, status);
    }

    @Override
    public Page<Order> findOrdersWithStatus(Pageable pageable, OrderStatus status) {
        return orderRepository.findAllByStatus(status, pageable);
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
                + tariff.getAdditional()).setScale(1, BigDecimal.ROUND_HALF_UP);
    }

    private float getDistance(City cityFrom, City cityTo) {
        float lat1 = cityFrom.getLatitude();
        float lat2 = cityTo.getLatitude();
        float lng1 = cityFrom.getLongitude();
        float lng2 = cityTo.getLongitude();

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (EARTH_RADIUS * c) / 1000;
    }
}
