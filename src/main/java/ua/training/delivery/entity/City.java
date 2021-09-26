package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
//    @Pattern(regexp = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$")

    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "name_uk")
    private String nameUk;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;


}
