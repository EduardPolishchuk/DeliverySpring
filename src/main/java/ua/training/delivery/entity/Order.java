package ua.training.delivery.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private LocalDate receivingDate;
    private LocalDate requestDate;
    private long id;
    private User userSender;
    private ua.training.delivery.entity.Parcel parcel;
    private ua.training.delivery.entity.City cityTo;
    private ua.training.delivery.entity.City cityFrom;
    private OrderStatus status;



    public enum OrderStatus {
        WAITING_FOR_CONFIRM,
        WAITING_FOR_PAYMENT,
        PARCEL_DELIVERY,
        DELIVERED,
        CANCELED
    }
}
