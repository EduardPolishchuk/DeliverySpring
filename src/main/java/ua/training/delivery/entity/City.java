package ua.training.delivery.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor

@Entity
@Table( name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String nameUk;
    private float longitude;
    private float latitude;


}
