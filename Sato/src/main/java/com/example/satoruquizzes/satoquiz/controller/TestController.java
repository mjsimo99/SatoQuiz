package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.TestDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.TestResDTO;
import com.example.satoruquizzes.satoquiz.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
@CrossOrigin
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/add")
    public ResponseEntity<TestDTO> addTest(@RequestBody TestDTO testDTO) {
        TestDTO savedTestDTO = testService.save(testDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestResDTO>> getAllTests() {
        List<TestResDTO> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{testId}")
    public ResponseEntity<TestResDTO> getTestById(@PathVariable Long testId) {
        TestResDTO testResDTO = testService.getTestById(testId);
        return ResponseEntity.ok(testResDTO);
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