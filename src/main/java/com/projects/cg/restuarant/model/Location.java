package com.projects.cg.restuarant.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collection = "locations")
public class Location {

    @Id
    private String lid; // ObjectId will be used by MongoDB by default
    private String locationName;
    private String address;
    private List<LocationItemAvailability> itemAvailabilityList;

    // Constructors
    public Location() {
    }

    public Location(String lid, String locationName, String address,
            List<LocationItemAvailability> itemAvailabilityList) {
        this.lid = lid;
        this.locationName = locationName;
        this.address = address;
        this.itemAvailabilityList = itemAvailabilityList;
    }

    // Getters and Setters
    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<LocationItemAvailability> getItemAvailabilityList() {
        return itemAvailabilityList;
    }

    public void setItemAvailabilityList(List<LocationItemAvailability> itemAvailabilityList) {
        this.itemAvailabilityList = itemAvailabilityList;
    }
}