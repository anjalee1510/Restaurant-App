package com.projects.cg.restuarant.service.impl;

import com.projects.cg.restuarant.model.Location;
import com.projects.cg.restuarant.repository.LocationRepository;
import com.projects.cg.restuarant.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Override
    public List<Location> getAllLocations() {
        return repository.findAll();
    }

    @Override
    public Optional<Location> getLocationById(String id) {
        return repository.findById(id);
    }

    @Override
    public Location createLocation(Location location) {
        return repository.save(location);
    }

    @Override
    public Optional<Location> updateLocation(String id, Location location) {
        Optional<Location> existingLocationOpt = repository.findById(id);
        if (existingLocationOpt.isPresent()) {
            Location existingLocation = existingLocationOpt.get();
            
            existingLocation.setLocationName(location.getLocationName());
            existingLocation.setAddress(location.getAddress());
            existingLocation.setItemAvailabilityList(location.getItemAvailabilityList());
            repository.save(existingLocation);
            return Optional.of(existingLocation);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteLocation(String id) {
        return repository.findById(id)
                .map(existingLocation -> {
                    repository.delete(existingLocation);
                    return true;
                }).orElse(false);
    }
}