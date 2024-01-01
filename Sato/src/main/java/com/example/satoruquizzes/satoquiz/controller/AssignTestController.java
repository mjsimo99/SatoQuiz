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

    public AssignTestController(AssignTestService assignTestService) {
        this.assignTestService = assignTestService;
    }

    @PostMapping("/add")
    public ResponseEntity<AssignTestDTO> addAssignTest(@RequestBody AssignTestDTO assignTestDTO) {
        AssignTestDTO savedAssignTestDTO = assignTestService.save(assignTestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignTestDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AssignTestRespDTO>> getAllAssignTests() {
        List<AssignTestRespDTO> assignTests = assignTestService.getAllAssignTests();
        return ResponseEntity.ok(assignTests);
    }

    @GetMapping("/{assignTestId}")
    public ResponseEntity<AssignTestRespDTO> getAssignTestById(@PathVariable Long assignTestId) {
        AssignTestRespDTO assignTestRespDTO = assignTestService.getAssignTestById(assignTestId);
        return ResponseEntity.ok(assignTestRespDTO);
    }

    @PutMapping("/update/{assignTestId}")
    public ResponseEntity<AssignTestDTO> updateAssignTest(@PathVariable Long assignTestId, @RequestBody AssignTestDTO updatedAssignTestDTO) {
        AssignTestDTO updatedEntityDTO = assignTestService.update(assignTestId, updatedAssignTestDTO);
        return ResponseEntity.ok(updatedEntityDTO);
    }

    @PutMapping("/update-attempt-number/{assignTestId}/{newAttemptNumber}")
    public ResponseEntity<Void> updateAttemptNumber(@PathVariable Long assignTestId, @PathVariable Integer newAttemptNumber) {
        assignTestService.updateAttemptNumber(assignTestId, newAttemptNumber);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{assignTestId}")
    public ResponseEntity<Void> deleteAssignTest(@PathVariable Long assignTestId) {
        assignTestService.deleteAssignTest(assignTestId);
        return ResponseEntity.noContent().build();
    }
}
