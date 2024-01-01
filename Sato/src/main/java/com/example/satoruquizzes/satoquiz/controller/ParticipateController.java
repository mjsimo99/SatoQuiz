package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.ParticipateDTO;
import com.example.satoruquizzes.satoquiz.service.ParticipateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participates")
public class ParticipateController {

    private final ParticipateService participateService;

    public ParticipateController(ParticipateService participateService) {
        this.participateService = participateService;
    }


    @PostMapping
    public ResponseEntity<ParticipateDTO> saveParticipate(@RequestBody ParticipateDTO participateDTO) {
        ParticipateDTO savedParticipate = participateService.saveParticipate(participateDTO);
        return new ResponseEntity<>(savedParticipate, HttpStatus.CREATED);
    }
}
