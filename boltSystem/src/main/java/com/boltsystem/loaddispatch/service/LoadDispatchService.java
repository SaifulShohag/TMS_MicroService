package com.boltsystem.loaddispatch.service;

import com.boltsystem.loaddispatch.entity.Driver;
import com.boltsystem.loaddispatch.entity.Load;
import com.boltsystem.loaddispatch.entity.LoadStatus;
import com.boltsystem.loaddispatch.repository.DriverRepository;
import com.boltsystem.loaddispatch.repository.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoadDispatchService {
    @Autowired
    private LoadRepository loadRepository;
    @Autowired
    private DriverRepository driverRepository;

    public Load createLoad( String description ) {
        Load load = new Load( description );
        return loadRepository.save(load);
    }

    public Load dispatchLoad( Long loadId ) {
        Optional<Load> optionalLoad = loadRepository.findById( loadId );
        if( optionalLoad.isEmpty() ) {
            throw new RuntimeException("Load not found!");
        }

        Load load = optionalLoad.get();
        if( load.getStatus() != LoadStatus.PENDING ) {
            throw new RuntimeException("Load already dispatched!");
        }

        Optional<Driver> availableDriver = driverRepository.findByAvailableTrue();
        if( availableDriver.isEmpty() ) {
            throw new RuntimeException("No available driver!");
        }

        Driver driver = availableDriver.get();
        driver.setAvailable(false);
        driverRepository.save(driver);

        load.setStatus( LoadStatus.DISPATCHED );
        load.setAssignedDriverId( driver.getId() );
        
        return loadRepository.save(load);
    }

    public Load updateLoadStatus( Long loadId, LoadStatus status ) {
        Optional<Load> optionalLoad = loadRepository.findById( loadId );
        if( optionalLoad.isEmpty() ) {
            throw new RuntimeException("Load not found!");
        }

        Load load = optionalLoad.get();
        load.setStatus( status );
        
        if( status == LoadStatus.DELIVERED ) {

            if( load.getAssignedDriverId() != null ) {
                Optional<Driver> driver = driverRepository.findById( load.getAssignedDriverId() );
                driver.ifPresent( d -> {
                    d.setAvailable( true );
                    driverRepository.save( d );
                });
            }
        }

        return loadRepository.save( load );
    }

    public List<Load> getAllLoads () {
        return loadRepository.findAll();
    }
}
