package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.TestQuestionDTO;
import com.example.satoruquizzes.satoquiz.service.TestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testquestions")
@CrossOrigin
public class TestQuestionController {

    @Autowired
    private TestQuestionService testQuestionService;

    @GetMapping("/by-test/{testId}")
    public List<TestQuestionDTO> getTestQuestionsByTestId(@PathVariable Long testId) {
        return testQuestionService.getTestQuestionsByTestId(testId);
    }
    @PostMapping("/add")
    public ResponseEntity<TestQuestionDTO> addTestQuestion(@RequestBody TestQuestionDTO testQuestionDTO) {
        TestQuestionDTO savedTestQuestionDTO = testQuestionService.save(testQuestionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestQuestionDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestQuestionDTO>> getAllTestQuestions() {
        List<TestQuestionDTO> testQuestions = testQuestionService.getAllTestQuestions();
        return ResponseEntity.ok(testQuestions);
    }

    @GetMapping("/{testId}/{questionId}")
    public ResponseEntity<TestQuestionDTO> getTestQuestionById(@PathVariable Long testId, @PathVariable Long questionId) {
        TestQuestionDTO testQuestionDTO = testQuestionService.getTestQuestionById(testId, questionId);
        return ResponseEntity.ok(testQuestionDTO);
    }
}