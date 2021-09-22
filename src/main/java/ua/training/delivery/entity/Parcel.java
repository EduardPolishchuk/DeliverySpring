package ua.training.delivery.entity;

import java.util.Objects;

public class Parcel {

    private long id;
    private String type;
    private float length;
    private float width;
    private float height;
    private float weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return id == parcel.id && Float.compare(parcel.length, length) == 0 && Float.compare(parcel.width, width) == 0 && Float.compare(parcel.height, height) == 0 && Float.compare(parcel.weight, weight) == 0 && type.equals(parcel.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, length, width, height, weight);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    public static ParcelBuilder builder() {
        return new ParcelBuilder();
    }

    public static class ParcelBuilder {
        private final Parcel newParcel;

        private ParcelBuilder() {
            newParcel = new Parcel();
        }

        public ParcelBuilder type(String type) {
            newParcel.setType(type);
            return this;
        }

        public ParcelBuilder id(long id) {
            newParcel.setId(id);
            return this;
        }

        public ParcelBuilder length(float length) {
            newParcel.setLength(length);
            return this;
        }

        public ParcelBuilder width(float width) {
            newParcel.setWidth(width);
            return this;
        }

        public ParcelBuilder height(float height) {
            newParcel.setHeight(height);
            return this;
        }

        public ParcelBuilder weight(float weight) {
            newParcel.setWeight(weight);
            return this;
        }

        public Parcel build() {
            return newParcel;
        }
    }
}
