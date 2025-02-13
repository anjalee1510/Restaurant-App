package com.projects.cg.restuarant.repository;

import com.projects.cg.restuarant.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
}
