package ua.training.delivery.entity;

import java.util.Objects;

public class City {
    private String name;
    private String nameUk;
    private long id;
    private float longitude;
    private float latitude;

    public String getNameUk() {
        return nameUk;
    }

    public void setNameUk(String nameUk) {
        this.nameUk = nameUk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && Float.compare(city.longitude, longitude) == 0 && Float.compare(city.latitude, latitude) == 0 && name.equals(city.name) && nameUk.equals(city.nameUk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nameUk, id, longitude, latitude);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", nameUk='" + nameUk + '\'' +
                ", id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public static CityBuilder builder(){
        return new CityBuilder();
    }

    public static class CityBuilder {
        private final City newCity;

        private CityBuilder() {
            newCity = new City();
        }

        public CityBuilder name(String name) {
            newCity.setName(name);
            return this;
        }

        public CityBuilder nameUk(String nameUk) {
            newCity.setNameUk(nameUk);
            return this;
        }

        public CityBuilder id(long id) {
            newCity.setId(id);
            return this;
        }

        public CityBuilder latitude(float latitude) {
            newCity.setLatitude(latitude);
            return this;
        }

        public CityBuilder longitude(float longitude) {
            newCity.setLongitude(longitude);
            return this;
        }

        public City build (){
            return newCity;
        }
    }
}
