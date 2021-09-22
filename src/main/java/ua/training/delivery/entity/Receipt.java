package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Builder
@Data
@NoArgsConstructor

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private boolean paid;
    @OneToOne
    private Order order;

}
