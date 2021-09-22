package ua.training.delivery.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class Tariff {
    long id;
    float uahPerKilometerDistance;
    float uahPerMillimeterLength;
    float uahPerMillimeterWidth;
    float uahPerMillimeterHeight;
    float uahPerKilogramWeight;
    float additional;


}
