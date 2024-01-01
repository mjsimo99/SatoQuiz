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

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/add")
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody SubjectDTO subjectDTO) {
        SubjectDTO savedSubjectDTO = subjectService.save(subjectDTO);
        return ResponseEntity.ok(savedSubjectDTO);
    }

    @GetMapping("/all")
    public List<SubjectRespDTO> getAllSubjects() {
        return subjectService.getAll();
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectRespDTO> getSubjectById(@PathVariable Long subjectId) {
        SubjectRespDTO subjectRespDTO = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subjectRespDTO);
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDTO updatedSubjectDTO) {
        SubjectDTO updatedSubject = subjectService.update(subjectId, updatedSubjectDTO);
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId) {
        subjectService.delete(subjectId);
        return ResponseEntity.noContent().build();
    }
}