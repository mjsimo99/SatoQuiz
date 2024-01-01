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

    public TestQuestionController(TestQuestionService testQuestionService) {
        this.testQuestionService = testQuestionService;
    }

    @GetMapping("/by-test/{testId}")
    public List<TestQuestionRespDTO> getTestQuestionsByTestId(@PathVariable Long testId) {
        return testQuestionService.getTestQuestionsByTestId(testId);
    }
    @PostMapping("/add")
    public ResponseEntity<TestQuestionDTO> addTestQuestion(@RequestBody TestQuestionDTO testQuestionDTO) {
        TestQuestionDTO savedTestQuestionDTO = testQuestionService.save(testQuestionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestQuestionDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestQuestionRespDTO>> getAllTestQuestions() {
        List<TestQuestionRespDTO> testQuestionRespDTO = testQuestionService.getAllTestQuestions();
        return ResponseEntity.ok(testQuestionRespDTO);
    }

    @GetMapping("/{testId}/{questionId}")
    public ResponseEntity<TestQuestionRespDTO> getTestQuestionById(@PathVariable Long testId, @PathVariable Long questionId) {
        TestQuestionRespDTO testQuestionRespDTO = testQuestionService.getTestQuestionById(testId, questionId);
        return ResponseEntity.ok(testQuestionRespDTO);
    }
}