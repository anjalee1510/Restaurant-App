package com.projects.cg.restuarant.repository;

import com.projects.cg.restuarant.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepository extends MongoRepository<City, String> {
}
