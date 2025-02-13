package com.projects.cg.restuarant.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collection = "restaurant_brands")
public class RestaurantBrand {

    @Id
    private String id; // ObjectId will be used by MongoDB by default
    private String name;
    private List<City> cities;

    // Constructors
    public RestaurantBrand() {
    }

    public RestaurantBrand(String id, String name, List<City> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
