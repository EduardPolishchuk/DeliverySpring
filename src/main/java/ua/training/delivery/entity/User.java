package ua.training.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

import static ua.training.delivery.constants.Constants.*;

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

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Pattern(regexp = FIRST_NAME_REGEX)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Pattern(regexp = LAST_NAME_REGEX)
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Pattern(regexp = PASSWORD_REGEX)
    @Column(name = "password", nullable = false)
    private String password;

    @Pattern(regexp = EMAIL_REGEX)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Pattern(regexp = LOGIN_REGEX)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "balance")
    private BigDecimal balance;

}
