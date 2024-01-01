package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.AnswerDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.AnswerRespDTO;
import com.example.satoruquizzes.satoquiz.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswerController {

    private final AnswerService answerService;

    // Constructor injecting AnswerService dependency
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // Add a new answer
    @PostMapping("/add")
    public ResponseEntity<AnswerDTO> addAnswer(@RequestBody AnswerDTO answerDTO) {
        // Save the answer using the service
        AnswerDTO savedAnswerDTO = answerService.save(answerDTO);
        // Return the saved answer with HTTP status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnswerDTO);
    }

    // Get all answers
    @GetMapping("/all")
    public ResponseEntity<List<AnswerRespDTO>> getAllAnswers() {
        // Retrieve all answers using the service
        List<AnswerRespDTO> answers = answerService.getAllAnswers();
        // Return the list of answers
        return ResponseEntity.ok(answers);
    }

    // Get a specific answer by ID
    @GetMapping("/{answerId}")
    public ResponseEntity<AnswerRespDTO> getAnswerById(@PathVariable Long answerId) {
        // Retrieve the answer by ID using the service
        AnswerRespDTO answerRespDTO = answerService.getAnswerById(answerId);
        // Return the answer with HTTP status 200 (OK)
        return ResponseEntity.ok(answerRespDTO);
    }

    // Update an existing answer
    @PutMapping("/update/{answerId}")
    public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable Long answerId, @RequestBody AnswerDTO updatedAnswerDTO) {
        // Update the answer using the service
        AnswerDTO updatedEntityDTO = answerService.update(answerId, updatedAnswerDTO);
        // Return the updated answer
        return ResponseEntity.ok(updatedEntityDTO);
    }

    // Delete a specific answer by ID
    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        // Delete the answer using the service
        answerService.deleteAnswer(answerId);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
