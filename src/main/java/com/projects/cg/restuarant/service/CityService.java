package com.projects.cg.restuarant.service;

import com.projects.cg.restuarant.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> getAllCities();

    Optional<City> getCityById(String id);

    City createCity(City city);

    Optional<City> updateCity(String id, City city);

    boolean deleteCity(String id);
}