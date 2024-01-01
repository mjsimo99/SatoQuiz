package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.ParticipateDTO;
import com.example.satoruquizzes.satoquiz.service.ParticipateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participates")
public class ParticipateController {

    private final ParticipateService participateService;

    // Constructor injecting ParticipateService dependency
    public ParticipateController(ParticipateService participateService) {
        this.participateService = participateService;
    }

    // Endpoint to save a participation
    @PostMapping
    public ResponseEntity<ParticipateDTO> saveParticipate(@RequestBody ParticipateDTO participateDTO) {
        // Save the participation using the service
        ParticipateDTO savedParticipate = participateService.saveParticipate(participateDTO);
        // Return the saved participation with HTTP status 201 (CREATED)
        return new ResponseEntity<>(savedParticipate, HttpStatus.CREATED);
    }
}
