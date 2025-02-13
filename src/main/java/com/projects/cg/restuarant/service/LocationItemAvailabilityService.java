package com.projects.cg.restuarant.service;

import com.projects.cg.restuarant.model.LocationItemAvailability;

import java.util.List;
import java.util.Optional;

public interface LocationItemAvailabilityService {
    List<LocationItemAvailability> getAllLocationItemAvailabilities();

    Optional<LocationItemAvailability> getLocationItemAvailabilityById(String id);

    LocationItemAvailability createLocationItemAvailability(LocationItemAvailability locationItemAvailability);

    Optional<LocationItemAvailability> updateLocationItemAvailability(String id,
            LocationItemAvailability locationItemAvailability);

    boolean deleteLocationItemAvailability(String id);
}
