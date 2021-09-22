package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
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

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_sender")
    private User userSender;

    @OneToOne()
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @ManyToOne(fetch = FetchType.EAGER)
    private City cityTo;

    @ManyToOne()
    private City cityFrom;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
