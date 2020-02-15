package com.epam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class OutcomeStatus {

    private Integer id;

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("date")
    @Expose
    private String date;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OutcomeStatus{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutcomeStatus)) return false;
        OutcomeStatus that = (OutcomeStatus) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCategory(), that.getCategory()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategory(), getDate());
    }
}
