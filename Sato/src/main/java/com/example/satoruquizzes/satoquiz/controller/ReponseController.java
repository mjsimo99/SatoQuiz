package com.example.satoruquizzes.satoquiz.controller;


import com.example.satoruquizzes.satoquiz.model.dto.ReponseDTO;
import com.example.satoruquizzes.satoquiz.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reponses")
public class ReponseController {

    @Autowired
    private ReponseService reponseService;

    @PostMapping("/add")
    public ResponseEntity<ReponseDTO> addReponse(@RequestBody ReponseDTO reponseDTO) {
        ReponseDTO savedReponseDTO = reponseService.save(reponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReponseDTO>> getAllReponses() {
        List<ReponseDTO> reponses = reponseService.getAllReponses();
        return ResponseEntity.ok(reponses);
    }

    @GetMapping("/{reponseId}")
    public ResponseEntity<ReponseDTO> getReponseById(@PathVariable Long reponseId) {
        ReponseDTO reponseDTO = reponseService.getReponseById(reponseId);
        return ResponseEntity.ok(reponseDTO);
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