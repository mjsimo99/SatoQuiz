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

    // Constructor injecting ReponseService dependency
    public ReponseController(ReponseService reponseService) {
        this.reponseService = reponseService;
    }

    // Add a new response
    @PostMapping("/add")
    public ResponseEntity<ReponseDTO> addReponse(@RequestBody ReponseDTO reponseDTO) {
        // Save the response using the service
        ReponseDTO savedReponseDTO = reponseService.save(reponseDTO);
        // Return the saved response with HTTP status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReponseDTO);
    }

    // Delete all responses by assignTestId
    @DeleteMapping("/delete-all/{assignTestId}")
    public ResponseEntity<Void> deleteAllResponsesByAssignTestId(@PathVariable Long assignTestId) {
        try {
            // Delete all responses by assignTestId using the service
            reponseService.deleteAllResponses(assignTestId);
            // Return a no-content response with HTTP status 204 (NO CONTENT)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Return an internal server error response with HTTP status 500 (INTERNAL SERVER ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all responses
    @GetMapping("/all")
    public ResponseEntity<List<ReponseRespDto>> getAllReponses() {
        // Retrieve all responses using the service
        List<ReponseRespDto> reponses = reponseService.getAllReponses();
        // Return the list of responses with HTTP status 200 (OK)
        return ResponseEntity.ok(reponses);
    }

    // Get a specific response by ID
    @GetMapping("/{reponseId}")
    public ResponseEntity<ReponseRespDto> getReponseById(@PathVariable Long reponseId) {
        // Retrieve the response by ID using the service
        ReponseRespDto reponseRespDto = reponseService.getReponseById(reponseId);
        // Return the response with HTTP status 200 (OK)
        return ResponseEntity.ok(reponseRespDto);
    }

    // Update an existing response
    @PutMapping("/update/{reponseId}")
    public ResponseEntity<ReponseDTO> updateReponse(@PathVariable Long reponseId, @RequestBody ReponseDTO updatedReponseDTO) {
        // Update the response using the service
        ReponseDTO updatedEntityDTO = reponseService.update(reponseId, updatedReponseDTO);
        // Return the updated response with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedEntityDTO);
    }

    // Delete a specific response by ID
    @DeleteMapping("/{reponseId}")
    public ResponseEntity<Void> deleteReponse(@PathVariable Long reponseId) {
        // Delete the response by ID using the service
        reponseService.deleteReponse(reponseId);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
