package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.TestQuestionDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.TestQuestionRespDTO;
import com.example.satoruquizzes.satoquiz.service.TestQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testquestions")
@CrossOrigin
public class TestQuestionController {

    private final TestQuestionService testQuestionService;

    // Constructor injecting TestQuestionService dependency
    public TestQuestionController(TestQuestionService testQuestionService) {
        this.testQuestionService = testQuestionService;
    }

    // Get all test questions for a specific test by ID
    @GetMapping("/by-test/{testId}")
    public List<TestQuestionRespDTO> getTestQuestionsByTestId(@PathVariable Long testId) {
        // Retrieve test questions by test ID using the service
        return testQuestionService.getTestQuestionsByTestId(testId);
    }

    // Add a new test question
    @PostMapping("/add")
    public ResponseEntity<TestQuestionDTO> addTestQuestion(@RequestBody TestQuestionDTO testQuestionDTO) {
        // Save the test question using the service
        TestQuestionDTO savedTestQuestionDTO = testQuestionService.save(testQuestionDTO);
        // Return the saved test question with HTTP status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestQuestionDTO);
    }

    // Get all test questions
    @GetMapping("/all")
    public ResponseEntity<List<TestQuestionRespDTO>> getAllTestQuestions() {
        // Retrieve all test questions using the service
        List<TestQuestionRespDTO> testQuestionRespDTO = testQuestionService.getAllTestQuestions();
        // Return the list of test questions with HTTP status 200 (OK)
        return ResponseEntity.ok(testQuestionRespDTO);
    }

    // Get a specific test question by test ID and question ID
    @GetMapping("/{testId}/{questionId}")
    public ResponseEntity<TestQuestionRespDTO> getTestQuestionById(@PathVariable Long testId, @PathVariable Long questionId) {
        // Retrieve the test question by test ID and question ID using the service
        TestQuestionRespDTO testQuestionRespDTO = testQuestionService.getTestQuestionById(testId, questionId);
        // Return the test question with HTTP status 200 (OK)
        return ResponseEntity.ok(testQuestionRespDTO);
    }
}
