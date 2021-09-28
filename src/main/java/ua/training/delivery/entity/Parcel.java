package ua.training.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "parcel")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String type;


    @Min(value = 1)
    @Max(value = 1000)
    @Column(name = "length")
    private float length;


    @Min(value = 1)
    @Max(value = 1000)
    @Column(name = "width")
    private float width;


    @Min(value = 1)
    @Max(value = 2000)
    @Column(name = "height")
    private float height;


    @Min(value = 1)
    @Max(value = 200)
    @Column(name = "weight")
    private float weight;

}
