package com.projects.cg.restuarant.service.impl;

import com.projects.cg.restuarant.model.LocationItemAvailability;
import com.projects.cg.restuarant.repository.LocationItemAvailabilityRepository;
import com.projects.cg.restuarant.service.LocationItemAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationItemAvailabilityServiceImpl implements LocationItemAvailabilityService {

    @Autowired
    private LocationItemAvailabilityRepository repository;

    @Override
    public List<LocationItemAvailability> getAllLocationItemAvailabilities() {
        return repository.findAll();
    }

    @Override
    public Optional<LocationItemAvailability> getLocationItemAvailabilityById(String id) {
        return repository.findById(id);
    }

    @Override
    public LocationItemAvailability createLocationItemAvailability(LocationItemAvailability locationItemAvailability) {
        return repository.save(locationItemAvailability);
    }

    @Override
    public Optional<LocationItemAvailability> updateLocationItemAvailability(String id, LocationItemAvailability locationItemAvailability) {
        Optional<LocationItemAvailability> existingItemOpt = repository.findById(id);
        if (existingItemOpt.isPresent()) {
            LocationItemAvailability existingItem = existingItemOpt.get();
            existingItem.setItemName(locationItemAvailability.getItemName());
            existingItem.setAvailable(locationItemAvailability.isAvailable());
            existingItem.setReasonUnavailable(locationItemAvailability.getReasonUnavailable());
            repository.save(existingItem);
            return Optional.of(existingItem);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteLocationItemAvailability(String id) {
        return repository.findById(id)
                .map(existingItem -> {
                    repository.delete(existingItem);
                    return true;
                }).orElse(false);
    }
}
