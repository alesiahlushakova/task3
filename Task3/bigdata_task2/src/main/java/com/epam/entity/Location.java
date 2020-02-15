
package com.epam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class Location {

    @SerializedName("latitude")
    @Expose
    private float latitude;
    @SerializedName("street")
    @Expose
    private Street street;
    @SerializedName("longitude")
    @Expose
    private float longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    private int streetId;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Float.compare(location.getLatitude(), getLatitude()) == 0 &&
                Float.compare(location.getLongitude(), getLongitude()) == 0 &&
                getId() == location.getId() &&
                getStreet().equals(location.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLatitude(), getStreet(), getLongitude(), getId());
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", street=" + street +
                ", longitude=" + longitude +
                ", id=" + id +
                '}';
    }
}
