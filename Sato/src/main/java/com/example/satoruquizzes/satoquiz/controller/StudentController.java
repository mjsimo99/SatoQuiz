package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.StudentDTO;
import com.example.satoruquizzes.satoquiz.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    // Constructor injecting StudentService dependency
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Add a new student
    @PostMapping("/add")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        // Save the student using the service
        StudentDTO savedStudentDTO = studentService.save(studentDTO);
        // Return the saved student with HTTP status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudentDTO);
    }

    // Get all students
    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        // Retrieve all students using the service
        List<StudentDTO> students = studentService.getAllStudents();
        // Return the list of students with HTTP status 200 (OK)
        return ResponseEntity.ok(students);
    }

    // Get a specific student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
        // Retrieve the student by ID using the service
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        // Return the student with HTTP status 200 (OK)
        return ResponseEntity.ok(studentDTO);
    }

    // Update an existing student
    @PutMapping("/update/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO updatedStudentDTO) {
        // Update the student using the service
        StudentDTO updatedEntityDTO = studentService.update(studentId, updatedStudentDTO);
        // Return the updated student with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedEntityDTO);
    }

    // Delete a specific student by ID
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        // Delete the student by ID using the service
        studentService.deleteStudent(studentId);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
