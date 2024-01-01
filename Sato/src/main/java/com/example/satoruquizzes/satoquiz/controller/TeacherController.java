package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.TeacherDTO;
import com.example.satoruquizzes.satoquiz.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@CrossOrigin
public class TeacherController {

    private final TeacherService teacherService;

    // Constructor injecting TeacherService dependency
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // Add a new teacher
    @PostMapping("/add")
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherDTO teacherDTO) {
        // Save the teacher using the service
        TeacherDTO savedTeacherDTO = teacherService.save(teacherDTO);
        // Return the saved teacher with HTTP status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacherDTO);
    }

    // Get all teachers
    @GetMapping("/all")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        // Retrieve all teachers using the service
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        // Return the list of teachers with HTTP status 200 (OK)
        return ResponseEntity.ok(teachers);
    }

    // Get a specific teacher by ID
    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long teacherId) {
        // Retrieve the teacher by ID using the service
        TeacherDTO teacherDTO = teacherService.getTeacherById(teacherId);
        // Return the teacher with HTTP status 200 (OK)
        return ResponseEntity.ok(teacherDTO);
    }

    // Update an existing teacher by ID
    @PutMapping("/update/{teacherId}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDTO updatedTeacherDTO) {
        // Update the teacher by ID using the service
        TeacherDTO updatedEntityDTO = teacherService.update(teacherId, updatedTeacherDTO);
        // Return the updated teacher with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedEntityDTO);
    }

    // Delete a specific teacher by ID
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        // Delete the teacher by ID using the service
        teacherService.deleteTeacher(teacherId);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
