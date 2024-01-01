package com.example.satoruquizzes.satoquiz.controller;


import com.example.satoruquizzes.satoquiz.model.dto.ReponseDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ReponseRespDto;
import com.example.satoruquizzes.satoquiz.service.ReponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reponses")
@CrossOrigin
public class ReponseController {

    private final ReponseService reponseService;

    public ReponseController(ReponseService reponseService) {
        this.reponseService = reponseService;
    }

    @PostMapping("/add")
    public ResponseEntity<ReponseDTO> addReponse(@RequestBody ReponseDTO reponseDTO) {
        ReponseDTO savedReponseDTO = reponseService.save(reponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReponseDTO);
    }
    @DeleteMapping("/delete-all/{assignTestId}")
    public ResponseEntity<Void> deleteAllResponsesByAssignTestId(@PathVariable Long assignTestId) {
        try {
            reponseService.deleteAllResponses(assignTestId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReponseRespDto>> getAllReponses() {
        List<ReponseRespDto> reponses = reponseService.getAllReponses();
        return ResponseEntity.ok(reponses);
    }

    @GetMapping("/{reponseId}")
    public ResponseEntity<ReponseRespDto> getReponseById(@PathVariable Long reponseId) {
        ReponseRespDto reponseRespDto = reponseService.getReponseById(reponseId);
        return ResponseEntity.ok(reponseRespDto);
    }

    @PutMapping("/update/{reponseId}")
    public ResponseEntity<ReponseDTO> updateReponse(@PathVariable Long reponseId, @RequestBody ReponseDTO updatedReponseDTO) {
        ReponseDTO updatedEntityDTO = reponseService.update(reponseId, updatedReponseDTO);
        return ResponseEntity.ok(updatedEntityDTO);
    }

    @DeleteMapping("/{reponseId}")
    public ResponseEntity<Void> deleteReponse(@PathVariable Long reponseId) {
        reponseService.deleteReponse(reponseId);
        return ResponseEntity.noContent().build();
    }
}