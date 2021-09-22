package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_paid")
    private boolean paid;

    @OneToOne()
    @JoinColumn(name = "order_id")
    private Order order;

}
