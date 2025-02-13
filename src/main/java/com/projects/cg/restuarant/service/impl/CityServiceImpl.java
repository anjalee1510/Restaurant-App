package com.projects.cg.restuarant.service.impl;

import com.projects.cg.restuarant.model.City;
import com.projects.cg.restuarant.repository.CityRepository;
import com.projects.cg.restuarant.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<City> getAllCities() {
        return repository.findAll();
    }

    @Override
    public Optional<City> getCityById(String id) {
        return repository.findById(id);
    }

    @Override
    public City createCity(City city) {
        return repository.save(city);
    }

    @Override
    public Optional<City> updateCity(String id, City city) {
        Optional<City> existingCityOpt = repository.findById(id);
        if (existingCityOpt.isPresent()) {
            City existingCity = existingCityOpt.get();
            existingCity.setCityName(city.getCityName());
            existingCity.setLocations(city.getLocations());
            repository.save(existingCity);
            return Optional.of(existingCity);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteCity(String id) {
        return repository.findById(id)
                .map(existingCity -> {
                    repository.delete(existingCity);
                    return true;
                }).orElse(false);
    }
}
