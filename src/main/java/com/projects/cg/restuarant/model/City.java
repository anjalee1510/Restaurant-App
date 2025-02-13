package com.projects.cg.restuarant.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class City {

    @Id
    private String cid; // ObjectId will be used by MongoDB by default
    private String cityName;
    private List<Location> locations;

    public City() {
    }

    public City(String cid, String cityName, List<Location> locations) {
        this.cid = cid;
        this.cityName = cityName;
        this.locations = locations;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

}
