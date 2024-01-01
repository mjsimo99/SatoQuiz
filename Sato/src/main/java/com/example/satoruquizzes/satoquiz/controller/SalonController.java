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

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SalonRespDTO>> getAllSalons() {
        List<SalonRespDTO> salons = salonService.getAllSalons();
        return new ResponseEntity<>(salons, HttpStatus.OK);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonRespDTO> getSalonById(@PathVariable Long salonId) {
        SalonRespDTO salon = salonService.getSalonById(salonId);
        return new ResponseEntity<>(salon, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO) {
        SalonDTO createdSalon = salonService.createSalon(salonDTO);
        return new ResponseEntity<>(createdSalon, HttpStatus.CREATED);
    }

    @DeleteMapping("/{salonId}")
    public ResponseEntity<Void> deleteSalon(@PathVariable Long salonId) {
        salonService.deleteSalon(salonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
