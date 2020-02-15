
package com.epam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class Crime {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("context")
    @Expose
    private String context;
    @SerializedName("outcome_status")
    @Expose
    private OutcomeStatus outcomeStatus;
    @SerializedName("persistent_id")
    @Expose
    private String persistentId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("location_subtype")
    @Expose
    private String locationSubtype;
    @SerializedName("month")
    @Expose
    private String month;

    public int getOutcomeStatusId() {
        return outcomeStatusId;
    }

    public void setOutcomeStatusId(int outcomeStatusId) {
        this.outcomeStatusId = outcomeStatusId;
    }

    private int outcomeStatusId;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    private int locationId;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public OutcomeStatus getOutcomeStatus() {
        return outcomeStatus;
    }

    public void setOutcomeStatus(OutcomeStatus outcomeStatus) {
        this.outcomeStatus = outcomeStatus;
    }

    public String getPersistentId() {
        return persistentId;
    }

    public void setPersistentId(String persistentId) {
        this.persistentId = persistentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationSubtype() {
        return locationSubtype;
    }

    public void setLocationSubtype(String locationSubtype) {
        this.locationSubtype = locationSubtype;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Crime)) return false;
        Crime crime = (Crime) o;
        return getLocationId() == crime.getLocationId() &&
                Objects.equals(getCategory(), crime.getCategory()) &&
                Objects.equals(getLocationType(), crime.getLocationType()) &&
                Objects.equals(getLocation(), crime.getLocation()) &&
                Objects.equals(getContext(), crime.getContext()) &&
                Objects.equals(getOutcomeStatus(), crime.getOutcomeStatus()) &&
                Objects.equals(getPersistentId(), crime.getPersistentId()) &&
                Objects.equals(getId(), crime.getId()) &&
                Objects.equals(getLocationSubtype(), crime.getLocationSubtype()) &&
                Objects.equals(getMonth(), crime.getMonth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getLocationType(), getLocation(), getContext(), getOutcomeStatus(), getPersistentId(), getId(), getLocationSubtype(), getMonth(), getLocationId());
    }

    @Override
    public String toString() {
        return "Crime{" +
                "category='" + category + '\'' +
                ", locationType='" + locationType + '\'' +
                ", location=" + location +
                ", context='" + context + '\'' +
                ", outcomeStatus=" + outcomeStatus +
                ", persistentId='" + persistentId + '\'' +
                ", id=" + id +
                ", locationSubtype='" + locationSubtype + '\'' +
                ", month='" + month + '\'' +
                ", locationId=" + locationId +
                '}';
    }
}
