package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import static ua.training.delivery.constants.Constants.CITY_REGEX_EN;
import static ua.training.delivery.constants.Constants.CITY_REGEX_UK;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Pattern(regexp = CITY_REGEX_EN)
    @Column(name = "name", unique = true)
    private String name;


    @Pattern(regexp = CITY_REGEX_UK)
    @Column(name = "name_uk", unique = true)
    private String nameUk;


    @Column(name = "longitude")
    private float longitude;


    @Column(name = "latitude")
    private float latitude;


}
