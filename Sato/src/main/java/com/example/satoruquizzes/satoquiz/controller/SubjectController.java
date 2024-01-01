package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.SubjectDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.SubjectRespDTO;
import com.example.satoruquizzes.satoquiz.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@CrossOrigin
public class SubjectController {

    private final SubjectService subjectService;

    // Constructor injecting SubjectService dependency
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // Add a new subject
    @PostMapping("/add")
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody SubjectDTO subjectDTO) {
        // Save the subject using the service
        SubjectDTO savedSubjectDTO = subjectService.save(subjectDTO);
        // Return the saved subject with HTTP status 200 (OK)
        return ResponseEntity.ok(savedSubjectDTO);
    }

    // Get all subjects
    @GetMapping("/all")
    public List<SubjectRespDTO> getAllSubjects() {
        // Retrieve all subjects using the service
        return subjectService.getAll();
    }

    // Get a specific subject by ID
    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectRespDTO> getSubjectById(@PathVariable Long subjectId) {
        // Retrieve the subject by ID using the service
        SubjectRespDTO subjectRespDTO = subjectService.getSubjectById(subjectId);
        // Return the subject with HTTP status 200 (OK)
        return ResponseEntity.ok(subjectRespDTO);
    }

    // Update an existing subject by ID
    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDTO updatedSubjectDTO) {
        // Update the subject by ID using the service
        SubjectDTO updatedSubject = subjectService.update(subjectId, updatedSubjectDTO);
        // Return the updated subject with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedSubject);
    }

    // Delete a specific subject by ID
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId) {
        // Delete the subject by ID using the service
        subjectService.delete(subjectId);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
