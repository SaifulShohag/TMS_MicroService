package com.boltsystem.loaddispatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boltsystem.loaddispatch.service.LoadDispatchService;
import com.boltsystem.loaddispatch.dto.CreateLoadRequest;
import com.boltsystem.loaddispatch.dto.LoadStatusUpdateRequest;
import com.boltsystem.loaddispatch.entity.Load;

@RestController
@RequestMapping("/api/loads")
public class LoadController {
    @Autowired
    private LoadDispatchService service;

    @PostMapping
    public ResponseEntity<Load> createLoad( @RequestBody CreateLoadRequest request ) {
        return ResponseEntity.ok( service.createLoad( request.getDescription() ) );
    }

    @PostMapping("/{id}/dispatch")
    public ResponseEntity<Load> dispatchLoad( @PathVariable Long id ) {
        return ResponseEntity.ok( service.dispatchLoad(id) );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Load> updateStatus( @PathVariable Long id, @RequestBody LoadStatusUpdateRequest request ) {
        return ResponseEntity.ok( service.updateLoadStatus(id, request.getStatus()) );
    }

    @GetMapping
    public ResponseEntity<List<Load>> getAllLoads() {
        return ResponseEntity.ok( service.getAllLoads() );
    }
}
