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

    // Constructor injecting TestService dependency
    public TestController(TestService testService) {
        this.testService = testService;
    }

    // Add a new test
    @PostMapping("/add")
    public ResponseEntity<TestDTO> addTest(@RequestBody TestDTO testDTO) {
        // Save the test using the service
        TestDTO savedTestDTO = testService.save(testDTO);
        // Return the saved test with HTTP status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestDTO);
    }

    // Get all tests
    @GetMapping("/all")
    public ResponseEntity<List<TestResDTO>> getAllTests() {
        // Retrieve all tests using the service
        List<TestResDTO> tests = testService.getAllTests();
        // Return the list of tests with HTTP status 200 (OK)
        return ResponseEntity.ok(tests);
    }

    // Get a specific test by ID
    @GetMapping("/{testId}")
    public ResponseEntity<TestResDTO> getTestById(@PathVariable Long testId) {
        // Retrieve the test by ID using the service
        TestResDTO testResDTO = testService.getTestById(testId);
        // Return the test with HTTP status 200 (OK)
        return ResponseEntity.ok(testResDTO);
    }

    // Update an existing test by ID
    @PutMapping("/update/{testId}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable Long testId, @RequestBody TestDTO updatedTestDTO) {
        // Update the test by ID using the service
        TestDTO updatedEntityDTO = testService.update(testId, updatedTestDTO);
        // Return the updated test with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedEntityDTO);
    }

    // Delete a specific test by ID
    @DeleteMapping("/{testId}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long testId) {
        // Delete the test by ID using the service
        testService.deleteTest(testId);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
