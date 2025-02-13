package com.projects.cg.restuarant.service;

import com.projects.cg.restuarant.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> getAllLocations();
    Optional<Location> getLocationById(String id);
    Location createLocation(Location location);
    Optional<Location> updateLocation(String id, Location location);
    boolean deleteLocation(String id);
}