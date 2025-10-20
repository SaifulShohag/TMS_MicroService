package com.boltsystem.loaddispatch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boltsystem.loaddispatch.dto.CreateDriverRequest;
import com.boltsystem.loaddispatch.dto.DriverAvailabilityUpdateRequest;
import com.boltsystem.loaddispatch.entity.Driver;
import com.boltsystem.loaddispatch.repository.DriverRepository;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok( driverRepository.findAll() );
    }

    @GetMapping("/available")
    public ResponseEntity<Optional<Driver>> getAvailableDrivers() {
        return ResponseEntity.ok( driverRepository.findByAvailableTrue() );
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver( @RequestBody CreateDriverRequest request ) {
        Driver driver = new Driver( request.getName() );
        return ResponseEntity.ok( driverRepository.save( driver ));
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Driver> updateAvailability( @PathVariable Long id, @RequestBody DriverAvailabilityUpdateRequest request ) {
        Driver driver = driverRepository.findById( id ).orElseThrow( 
            () -> new RuntimeException( "Driver not found" ) );
        driver.setAvailable( request.isAvailable() );
        return ResponseEntity.ok( driverRepository.save( driver ) );
    }
}
