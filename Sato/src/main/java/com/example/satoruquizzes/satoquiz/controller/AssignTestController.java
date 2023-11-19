package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.AssignTestDTO;
import com.example.satoruquizzes.satoquiz.service.AssignTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assign-tests")
public class AssignTestController {

    @Autowired
    private AssignTestService assignTestService;

    @PostMapping("/add")
    public ResponseEntity<AssignTestDTO> addAssignTest(@RequestBody AssignTestDTO assignTestDTO) {
        AssignTestDTO savedAssignTestDTO = assignTestService.save(assignTestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignTestDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AssignTestDTO>> getAllAssignTests() {
        List<AssignTestDTO> assignTests = assignTestService.getAllAssignTests();
        return ResponseEntity.ok(assignTests);
    }

    @GetMapping("/{assignTestId}")
    public ResponseEntity<AssignTestDTO> getAssignTestById(@PathVariable Long assignTestId) {
        AssignTestDTO assignTestDTO = assignTestService.getAssignTestById(assignTestId);
        return ResponseEntity.ok(assignTestDTO);
    }

    @PutMapping("/update/{assignTestId}")
    public ResponseEntity<AssignTestDTO> updateAssignTest(@PathVariable Long assignTestId, @RequestBody AssignTestDTO updatedAssignTestDTO) {
        AssignTestDTO updatedEntityDTO = assignTestService.update(assignTestId, updatedAssignTestDTO);
        return ResponseEntity.ok(updatedEntityDTO);
    }

    @DeleteMapping("/{assignTestId}")
    public ResponseEntity<Void> deleteAssignTest(@PathVariable Long assignTestId) {
        assignTestService.deleteAssignTest(assignTestId);
        return ResponseEntity.noContent().build();
    }
}
