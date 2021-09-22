package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "login",nullable = false)
    private String login;

    @Column(name = "balance")
    private BigDecimal balance;

}
