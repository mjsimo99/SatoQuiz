package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.TestDTO;
import com.example.satoruquizzes.satoquiz.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/add")
    public ResponseEntity<TestDTO> addTest(@RequestBody TestDTO testDTO) {
        TestDTO savedTestDTO = testService.save(testDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestDTO>> getAllTests() {
        List<TestDTO> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{testId}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Long testId) {
        TestDTO testDTO = testService.getTestById(testId);
        return ResponseEntity.ok(testDTO);
    }

    @PutMapping("/update/{testId}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable Long testId, @RequestBody TestDTO updatedTestDTO) {
        TestDTO updatedEntityDTO = testService.update(testId, updatedTestDTO);
        return ResponseEntity.ok(updatedEntityDTO);
    }

    @DeleteMapping("/{testId}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return ResponseEntity.noContent().build();
    }
}