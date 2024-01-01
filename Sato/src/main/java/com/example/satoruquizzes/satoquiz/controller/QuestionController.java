package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.QuestionRespDTO;
import com.example.satoruquizzes.satoquiz.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {

    private final QuestionService questionService;

    // Constructor injecting QuestionService dependency
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Add a new question
    @PostMapping("/add")
    public ResponseEntity<QuestionDTO> addQuestion(@RequestBody QuestionDTO questionDTO) {
        // Save the question using the service
        QuestionDTO savedQuestionDTO = questionService.save(questionDTO);
        // Return the saved question with HTTP status 200 (OK)
        return ResponseEntity.ok(savedQuestionDTO);
    }

    // Add a new question with media
    @PostMapping("/add-with-media")
    public ResponseEntity<QuestionDTO> addQuestionWithMedia(@RequestBody QuestionDTO questionDTO) {
        // Save the question with media using the service
        QuestionDTO savedQuestionDTO = questionService.saveWithMedia(questionDTO);
        // Return the saved question with HTTP status 200 (OK)
        return ResponseEntity.ok(savedQuestionDTO);
    }

    // Get all questions
    @GetMapping("/all")
    public List<QuestionRespDTO> getAllQuestions() {
        // Retrieve all questions using the service
        return questionService.getAll();
    }

    // Get a specific question by ID
    @GetMapping("/{id}")
    public ResponseEntity<QuestionRespDTO> getQuestionById(@PathVariable Long id) {
        // Retrieve the question by ID using the service
        QuestionRespDTO questionRespDTO = questionService.getById(id);
        // Return the question with HTTP status 200 (OK)
        return ResponseEntity.ok(questionRespDTO);
    }

    // Update an existing question
    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        // Update the question using the service
        QuestionDTO updatedQuestionDTO = questionService.update(id, questionDTO);
        // Return the updated question with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedQuestionDTO);
    }

    // Delete a specific question by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        // Delete the question using the service
        questionService.delete(id);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
