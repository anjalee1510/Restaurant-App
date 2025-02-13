package com.projects.cg.restuarant.controller;

import com.projects.cg.restuarant.model.LocationItemAvailability;
import com.projects.cg.restuarant.service.LocationItemAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location-item-availabilities")
public class LocationItemAvailabilityController {

    @Autowired
    private LocationItemAvailabilityService service;

    @GetMapping
    public List<LocationItemAvailability> getAllLocationItemAvailabilities() {
        return service.getAllLocationItemAvailabilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationItemAvailability> getLocationItemAvailabilityById(@PathVariable String id) {
        return service.getLocationItemAvailabilityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LocationItemAvailability createLocationItemAvailability(@RequestBody LocationItemAvailability locationItemAvailability) {
        return service.createLocationItemAvailability(locationItemAvailability);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationItemAvailability> updateLocationItemAvailability(@PathVariable String id, @RequestBody LocationItemAvailability locationItemAvailability) {
        return service.updateLocationItemAvailability(id, locationItemAvailability)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocationItemAvailability(@PathVariable String id) {
        return service.deleteLocationItemAvailability(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
