package ua.training.delivery.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor

@Entity
@Table(name = "tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private float uahPerKilometerDistance;
   private float uahPerMillimeterLength;
   private float uahPerMillimeterWidth;
   private float uahPerMillimeterHeight;
   private float uahPerKilogramWeight;
   private float additional;


}
