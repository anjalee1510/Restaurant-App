package com.projects.cg.restuarant.controller;

import com.projects.cg.restuarant.model.City;
import com.projects.cg.restuarant.model.Location;
import com.projects.cg.restuarant.model.RestaurantBrand;
import com.projects.cg.restuarant.service.RestaurantBrandService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurant-brands")
public class RestaurantBrandController {

    @Autowired
    private RestaurantBrandService service;

    @GetMapping
    public List<RestaurantBrand> getAllRestaurantBrands() {
        return service.getAllRestaurantBrands();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantBrand> getRestaurantBrandById(@PathVariable String id) {
        return service.getRestaurantBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RestaurantBrand createRestaurantBrand(@RequestBody RestaurantBrand restaurantBrand) {
        return service.createRestaurantBrand(restaurantBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantBrand> updateRestaurantBrand(@PathVariable String id,
            @RequestBody RestaurantBrand restaurantBrand) {
        return service.updateRestaurantBrand(id, restaurantBrand)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantBrand(@PathVariable String id) {
        return service.deleteRestaurantBrand(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/names")
    public ResponseEntity<List<RestaurantBrandNameDto>> getAllRestaurantBrandNames() {
        List<RestaurantBrandNameDto> restaurantBrandNames = service.getAllRestaurantBrands()
                .stream()
                .map(brand -> new RestaurantBrandNameDto(brand.getId(), brand.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantBrandNames);
    }

    public static class RestaurantBrandNameDto {
        private String id;
        private String name;

        public RestaurantBrandNameDto(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    @GetMapping("/{id}/city-names")
    public ResponseEntity<List<CityNameDto>> getCityNamesForRestaurantBrand(@PathVariable String id) {
        return service.getRestaurantBrandById(id)
                .map(restaurantBrand -> {
                    List<CityNameDto> cityNames = restaurantBrand.getCities()
                            .stream()
                            .map(city -> new CityNameDto(city.getCid(), city.getCityName()))
                            .collect(Collectors.toList());
                    return ResponseEntity.ok(cityNames);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public static class CityNameDto {
        private String id;
        private String name;

        public CityNameDto(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    @GetMapping("/{restaurantId}/{cityId}/locations")
    public ResponseEntity<List<LocationNameDto>> getLocationNamesForCity(@PathVariable String restaurantId,
            @PathVariable String cityId) {
        Optional<RestaurantBrand> restaurantBrandOpt = service.getRestaurantBrandById(restaurantId);
        if (restaurantBrandOpt.isPresent()) {
            RestaurantBrand restaurantBrand = restaurantBrandOpt.get();
            Optional<City> cityOpt = restaurantBrand.getCities().stream()
                    .filter(city -> city.getCid().equals(cityId))
                    .findFirst();
            if (cityOpt.isPresent()) {
                List<LocationNameDto> locationNames = cityOpt.get().getLocations()
                        .stream()
                        .map(location -> new LocationNameDto(location.getLid(), location.getLocationName()))
                        .collect(Collectors.toList());
                return ResponseEntity.ok(locationNames);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public static class LocationNameDto {
        private String id;
        private String name;

        public LocationNameDto(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    @GetMapping("/{restaurantId}/{cityId}/{locationId}/unavailable-items")
    public ResponseEntity<List<UnavailableItemDto>> getUnavailableItemsForLocation(@PathVariable String restaurantId,
            @PathVariable String cityId,
            @PathVariable String locationId) {
        Optional<RestaurantBrand> restaurantBrandOpt = service.getRestaurantBrandById(restaurantId);
        if (restaurantBrandOpt.isPresent()) {
            RestaurantBrand restaurantBrand = restaurantBrandOpt.get();
            Optional<City> cityOpt = restaurantBrand.getCities().stream()
                    .filter(city -> city.getCid().equals(cityId))
                    .findFirst();
            if (cityOpt.isPresent()) {
                Optional<Location> locationOpt = cityOpt.get().getLocations().stream()
                        .filter(location -> location.getLid().equals(locationId))
                        .findFirst();
                if (locationOpt.isPresent()) {
                    List<UnavailableItemDto> unavailableItems = locationOpt.get().getItemAvailabilityList().stream()
                            .filter(item -> !item.isAvailable())
                            .map(item -> new UnavailableItemDto(item.getItemName(), item.getReasonUnavailable()))
                            .collect(Collectors.toList());
                    return ResponseEntity.ok(unavailableItems);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    public static class UnavailableItemDto {
        private String itemName;
        private String reasonUnavailable;

        public UnavailableItemDto(String itemName, String reasonUnavailable) {
            this.itemName = itemName;
            this.reasonUnavailable = reasonUnavailable;
        }

        public String getItemName() {
            return itemName;
        }

        public String getReasonUnavailable() {
            return reasonUnavailable;
        }
    }

    @GetMapping("/unavailable-items")
    public ResponseEntity<List<UnavailableItemDetailsDto>> getAllUnavailableItems() {
        List<UnavailableItemDetailsDto> unavailableItems = service.getAllRestaurantBrands()
                .stream()
                .flatMap(restaurantBrand -> restaurantBrand.getCities().stream()
                        .flatMap(city -> city.getLocations().stream()
                                .flatMap(location -> location.getItemAvailabilityList().stream()
                                        .filter(item -> !item.isAvailable())
                                        .map(item -> new UnavailableItemDetailsDto(
                                                restaurantBrand.getName(),
                                                city.getCityName(),
                                                location.getLocationName(),
                                                item.getItemName(),
                                                item.getReasonUnavailable())))))
                .collect(Collectors.toList());
        return ResponseEntity.ok(unavailableItems);
    }

    public static class UnavailableItemDetailsDto {
        private String restaurantBrandName;
        private String cityName;
        private String locationName;
        private String itemName;
        private String reasonUnavailable;

        public UnavailableItemDetailsDto(String restaurantBrandName, String cityName, String locationName,
                String itemName, String reasonUnavailable) {
            this.restaurantBrandName = restaurantBrandName;
            this.cityName = cityName;
            this.locationName = locationName;
            this.itemName = itemName;
            this.reasonUnavailable = reasonUnavailable;
        }

        public String getRestaurantBrandName() {
            return restaurantBrandName;
        }

        public String getCityName() {
            return cityName;
        }

        public String getLocationName() {
            return locationName;
        }

        public String getItemName() {
            return itemName;
        }

        public String getReasonUnavailable() {
            return reasonUnavailable;
        }
    }
}
