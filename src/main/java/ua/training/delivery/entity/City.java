package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
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
