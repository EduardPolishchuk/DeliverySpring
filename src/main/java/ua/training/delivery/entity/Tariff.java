package ua.training.delivery.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uah_per_km_distance")
    private float uahPerKilometerDistance;

    @Column(name = "uah_per_mm_length")
    private float uahPerMillimeterLength;

    @Column(name = "uah_per_mm_width")
    private float uahPerMillimeterWidth;

    @Column(name = "uah_per_mm_height")
    private float uahPerMillimeterHeight;

    @Column(name = "uah_per_kg_weight")
    private float uahPerKilogramWeight;

    @Column(name = "additional")
    private float additional;


}
