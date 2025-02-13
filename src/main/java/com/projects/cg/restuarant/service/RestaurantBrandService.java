package com.projects.cg.restuarant.service;

import com.projects.cg.restuarant.model.RestaurantBrand;

import java.util.List;
import java.util.Optional;

public interface RestaurantBrandService {
    List<RestaurantBrand> getAllRestaurantBrands();

    Optional<RestaurantBrand> getRestaurantBrandById(String id);

    RestaurantBrand createRestaurantBrand(RestaurantBrand restaurantBrand);

    Optional<RestaurantBrand> updateRestaurantBrand(String id, RestaurantBrand restaurantBrand);

    boolean deleteRestaurantBrand(String id);
}
