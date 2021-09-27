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

    @NotNull(message =  "NUUUUUUUUUUUUUUUUUL")
    @Email(message = "=ne=")
    @Column(name = "type")
    private String type;


    @Min(value = 10, message = "MORE THEN 1! length")
    @Column(name = "length")
    private float length;


    @Min(value = 10, message = "MORE THEN 1! width")
    @Column(name = "width")
    private float width;


    @Max(value = 0, message = "MORE THEN 1! width")
    @Column(name = "height")
    private float height;


    @Negative(message =  "POSITIVE")
    @Column(name = "weight")
    private float weight;

}
