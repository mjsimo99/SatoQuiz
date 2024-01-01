package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.SalonDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.SalonRespDTO;
import com.example.satoruquizzes.satoquiz.service.SalonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salons")
@CrossOrigin
public class SalonController {

    private final SalonService salonService;

    // Constructor injecting SalonService dependency
    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    // Get all salons
    @GetMapping("/all")
    public ResponseEntity<List<SalonRespDTO>> getAllSalons() {
        // Retrieve all salons using the service
        List<SalonRespDTO> salons = salonService.getAllSalons();
        // Return the list of salons with HTTP status 200 (OK)
        return new ResponseEntity<>(salons, HttpStatus.OK);
    }

    // Get a specific salon by ID
    @GetMapping("/{salonId}")
    public ResponseEntity<SalonRespDTO> getSalonById(@PathVariable Long salonId) {
        // Retrieve the salon by ID using the service
        SalonRespDTO salon = salonService.getSalonById(salonId);
        // Return the salon with HTTP status 200 (OK)
        return new ResponseEntity<>(salon, HttpStatus.OK);
    }

    // Create a new salon
    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO) {
        // Create the salon using the service
        SalonDTO createdSalon = salonService.createSalon(salonDTO);
        // Return the created salon with HTTP status 201 (CREATED)
        return new ResponseEntity<>(createdSalon, HttpStatus.CREATED);
    }

    // Delete a specific salon by ID
    @DeleteMapping("/{salonId}")
    public ResponseEntity<Void> deleteSalon(@PathVariable Long salonId) {
        // Delete the salon by ID using the service
        salonService.deleteSalon(salonId);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
