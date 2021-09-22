package ua.training.delivery.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Data
@NoArgsConstructor

@Entity
@Table(name = "parcel")
public class Parcel {

    private long id;
    private String type;
    private float length;
    private float width;
    private float height;
    private float weight;

}
