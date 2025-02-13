package com.projects.cg.restuarant.repository;

import com.projects.cg.restuarant.model.LocationItemAvailability;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationItemAvailabilityRepository extends MongoRepository<LocationItemAvailability, String> {
}
