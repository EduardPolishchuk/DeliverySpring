package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "receiving_date")
    private LocalDate receivingDate;

    @Column (name = "request_date")
    private LocalDate requestDate;

    @Column (name = "user_sender")
    @ManyToOne(fetch = FetchType.EAGER)
    private User userSender;

    @OneToOne(mappedBy = "id")
    private Parcel parcel;

    @ManyToOne
    private City cityTo;

    @ManyToOne()
    private City cityFrom;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
