package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.AssignTestDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.AssignTestRespDTO;
import com.example.satoruquizzes.satoquiz.service.AssignTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assign-tests")
@CrossOrigin
public class AssignTestController {

    private final AssignTestService assignTestService;

    // Constructor injecting AssignTestService dependency
    public AssignTestController(AssignTestService assignTestService) {
        this.assignTestService = assignTestService;
    }

    // Add a new assigned test
    @PostMapping("/add")
    public ResponseEntity<AssignTestDTO> addAssignTest(@RequestBody AssignTestDTO assignTestDTO) {
        // Save the assigned test using the service
        AssignTestDTO savedAssignTestDTO = assignTestService.save(assignTestDTO);
        // Return the saved assigned test with HTTP status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignTestDTO);
    }

    // Get all assigned tests
    @GetMapping("/all")
    public ResponseEntity<List<AssignTestRespDTO>> getAllAssignTests() {
        // Retrieve all assigned tests using the service
        List<AssignTestRespDTO> assignTests = assignTestService.getAllAssignTests();
        // Return the list of assigned tests
        return ResponseEntity.ok(assignTests);
    }

    // Get a specific assigned test by ID
    @GetMapping("/{assignTestId}")
    public ResponseEntity<AssignTestRespDTO> getAssignTestById(@PathVariable Long assignTestId) {
        // Retrieve the assigned test by ID using the service
        AssignTestRespDTO assignTestRespDTO = assignTestService.getAssignTestById(assignTestId);
        // Return the assigned test with HTTP status 200 (OK)
        return ResponseEntity.ok(assignTestRespDTO);
    }

    // Update an existing assigned test
    @PutMapping("/update/{assignTestId}")
    public ResponseEntity<AssignTestDTO> updateAssignTest(@PathVariable Long assignTestId, @RequestBody AssignTestDTO updatedAssignTestDTO) {
        // Update the assigned test using the service
        AssignTestDTO updatedEntityDTO = assignTestService.update(assignTestId, updatedAssignTestDTO);
        // Return the updated assigned test
        return ResponseEntity.ok(updatedEntityDTO);
    }

    // Update the attempt number of an assigned test
    @PutMapping("/update-attempt-number/{assignTestId}/{newAttemptNumber}")
    public ResponseEntity<Void> updateAttemptNumber(@PathVariable Long assignTestId, @PathVariable Integer newAttemptNumber) {
        // Update the attempt number using the service
        assignTestService.updateAttemptNumber(assignTestId, newAttemptNumber);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }

    // Delete a specific assigned test by ID
    @DeleteMapping("/{assignTestId}")
    public ResponseEntity<Void> deleteAssignTest(@PathVariable Long assignTestId) {
        // Delete the assigned test using the service
        assignTestService.deleteAssignTest(assignTestId);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
