package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String login;
    private BigDecimal balance;

}
