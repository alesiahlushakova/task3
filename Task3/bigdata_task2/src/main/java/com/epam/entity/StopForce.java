package com.epam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.Objects;

public class StopForce {

    private long id;
    private int locationId;


    private String outcomeObjectId;
    @SerializedName("age_range")
    @Expose
    private String ageRange;
    @SerializedName("self_defined_ethnicity")
    @Expose
    private String selfDefinedEthnicity;
    @SerializedName("outcome_linked_to_object_of_search")
    @Expose
    private Object outcomeLinkedToObjectOfSearch;
    @SerializedName("datetime")
    @Expose
    private Date datetime;
    @SerializedName("removal_of_more_than_outer_clothing")
    @Expose
    private Object removalOfMoreThanOuterClothing;
    @SerializedName("operation")
    @Expose
    private Boolean operation;
    @SerializedName("officer_defined_ethnicity")
    @Expose
    private String officerDefinedEthnicity;
    @SerializedName("object_of_search")
    @Expose
    private String objectOfSearch;
    @SerializedName("involved_person")
    @Expose
    private Boolean involvedPerson;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("legislation")
    @Expose
    private String legislation;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("outcome")
    @Expose
    private Boolean outcome;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("operation_name")
    @Expose
    private Object operationName;


    @SerializedName("outcome_object")
    @Expose
    private OutcomeObject outcomeObject;

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getSelfDefinedEthnicity() {
        return selfDefinedEthnicity;
    }

    public void setSelfDefinedEthnicity(String selfDefinedEthnicity) {
        this.selfDefinedEthnicity = selfDefinedEthnicity;
    }

    public Object getOutcomeLinkedToObjectOfSearch() {
        return outcomeLinkedToObjectOfSearch;
    }

    public void setOutcomeLinkedToObjectOfSearch(Object outcomeLinkedToObjectOfSearch) {
        this.outcomeLinkedToObjectOfSearch = outcomeLinkedToObjectOfSearch;
    }

    public OutcomeObject getOutcomeObject() {
        return outcomeObject;
    }

    public void setOutcomeObject(OutcomeObject outcomeObject) {
        this.outcomeObject = outcomeObject;
    }

    public String getOutcomeObjectId() {
        return outcomeObjectId;
    }

    public void setOutcomeObjectId(String outcomeObjectId) {
        this.outcomeObjectId = outcomeObjectId;
    }
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Object getRemovalOfMoreThanOuterClothing() {
        return removalOfMoreThanOuterClothing;
    }

    public void setRemovalOfMoreThanOuterClothing(Object removalOfMoreThanOuterClothing) {
        this.removalOfMoreThanOuterClothing = removalOfMoreThanOuterClothing;
    }

    public Boolean getOperation() {
        return operation;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }


    public void setOperation(Boolean operation) {
        this.operation = operation;
    }

    public String getOfficerDefinedEthnicity() {
        return officerDefinedEthnicity;
    }

    public void setOfficerDefinedEthnicity(String officerDefinedEthnicity) {
        this.officerDefinedEthnicity = officerDefinedEthnicity;
    }

    public String getObjectOfSearch() {
        return objectOfSearch;
    }

    public void setObjectOfSearch(String objectOfSearch) {
        this.objectOfSearch = objectOfSearch;
    }

    public Boolean getInvolvedPerson() {
        return involvedPerson;
    }

    public void setInvolvedPerson(Boolean involvedPerson) {
        this.involvedPerson = involvedPerson;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLegislation() {
        return legislation;
    }

    public void setLegislation(String legislation) {
        this.legislation = legislation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getOutcome() {
        return outcome;
    }

    public void setOutcome(Boolean outcome) {
        this.outcome = outcome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getOperationName() {
        return operationName;
    }

    public void setOperationName(Object operationName) {
        this.operationName = operationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StopForce)) return false;
        StopForce stopForce = (StopForce) o;
        return Objects.equals(getAgeRange(), stopForce.getAgeRange()) &&
                Objects.equals(getSelfDefinedEthnicity(), stopForce.getSelfDefinedEthnicity()) &&
                Objects.equals(getOutcomeLinkedToObjectOfSearch(), stopForce.getOutcomeLinkedToObjectOfSearch()) &&
                Objects.equals(getDatetime(), stopForce.getDatetime()) &&
                Objects.equals(getRemovalOfMoreThanOuterClothing(), stopForce.getRemovalOfMoreThanOuterClothing()) &&
                Objects.equals(getOperation(), stopForce.getOperation()) &&
                Objects.equals(getOfficerDefinedEthnicity(), stopForce.getOfficerDefinedEthnicity()) &&
                Objects.equals(getObjectOfSearch(), stopForce.getObjectOfSearch()) &&
                Objects.equals(getInvolvedPerson(), stopForce.getInvolvedPerson()) &&
                Objects.equals(getGender(), stopForce.getGender()) &&
                Objects.equals(getLegislation(), stopForce.getLegislation()) &&
                Objects.equals(getLocation(), stopForce.getLocation()) &&
                Objects.equals(getOutcome(), stopForce.getOutcome()) &&
                Objects.equals(getType(), stopForce.getType()) &&
                Objects.equals(getOperationName(), stopForce.getOperationName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAgeRange(), getSelfDefinedEthnicity(), getOutcomeLinkedToObjectOfSearch(), getDatetime(), getRemovalOfMoreThanOuterClothing(), getOperation(), getOfficerDefinedEthnicity(), getObjectOfSearch(), getInvolvedPerson(), getGender(), getLegislation(), getLocation(), getOutcome(), getType(), getOperationName());
    }

    @Override
    public String toString() {
        return "Force{" +
                "ageRange='" + ageRange + '\'' +
                ", selfDefinedEthnicity='" + selfDefinedEthnicity + '\'' +
                ", outcomeLinkedToObjectOfSearch=" + outcomeLinkedToObjectOfSearch +
                ", datetime=" + datetime +
                ", removalOfMoreThanOuterClothing=" + removalOfMoreThanOuterClothing +
                ", operation=" + operation +
                ", officerDefinedEthnicity='" + officerDefinedEthnicity + '\'' +
                ", objectOfSearch='" + objectOfSearch + '\'' +
                ", involvedPerson=" + involvedPerson +
                ", gender='" + gender + '\'' +
                ", legislation='" + legislation + '\'' +
                ", location=" + location +
                ", outcome=" + outcome +
                ", type='" + type + '\'' +
                ", operationName=" + operationName +
                '}';
    }
}
