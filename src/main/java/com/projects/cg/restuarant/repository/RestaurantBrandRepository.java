package com.projects.cg.restuarant.repository;

import com.projects.cg.restuarant.model.RestaurantBrand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantBrandRepository extends MongoRepository<RestaurantBrand, String> {
}
