package com.projects.cg.restuarant.service.impl;

import com.projects.cg.restuarant.model.RestaurantBrand;
import com.projects.cg.restuarant.repository.RestaurantBrandRepository;
import com.projects.cg.restuarant.service.RestaurantBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantBrandServiceImpl implements RestaurantBrandService {

    @Autowired
    private RestaurantBrandRepository repository;

    @Override
    public List<RestaurantBrand> getAllRestaurantBrands() {
        return repository.findAll();
    }

    @Override
    public Optional<RestaurantBrand> getRestaurantBrandById(String id) {
        return repository.findById(id);
    }

    @Override
    public RestaurantBrand createRestaurantBrand(RestaurantBrand restaurantBrand) {
        return repository.save(restaurantBrand);
    }

    @Override
    public Optional<RestaurantBrand> updateRestaurantBrand(String id, RestaurantBrand restaurantBrand) {
        Optional<RestaurantBrand> existingBrandOpt = repository.findById(id);
        if (existingBrandOpt.isPresent()) {
            RestaurantBrand existingBrand = existingBrandOpt.get();
            existingBrand.setName(restaurantBrand.getName());
            existingBrand.setCities(restaurantBrand.getCities());
            repository.save(existingBrand);
            return Optional.of(existingBrand);
        } else {
            return Optional.empty();
        }
    }
    // public Optional<RestaurantBrand> updateRestaurantBrand(String id,
    // RestaurantBrand restaurantBrand) {
    // return repository.findById(id)
    // .map(existingBrand -> {
    // existingBrand.setName(restaurantBrand.getName());
    // existingBrand.setLocations(restaurantBrand.getLocations());
    // return Optional.of(repository.save(existingBrand));
    // });
    // }

    @Override
    public boolean deleteRestaurantBrand(String id) {
        return repository.findById(id)
                .map(existingBrand -> {
                    repository.delete(existingBrand);
                    return true;
                }).orElse(false);
    }
}
