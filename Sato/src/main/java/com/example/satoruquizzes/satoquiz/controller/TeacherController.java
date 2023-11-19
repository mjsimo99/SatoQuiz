package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.TeacherDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Teacher;
import com.example.satoruquizzes.satoquiz.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO savedTeacherDTO = teacherService.save(teacherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacherDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long teacherId) {
        TeacherDTO teacherDTO = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacherDTO);
    }

    @PutMapping("/update/{teacherId}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDTO updatedTeacherDTO) {
        TeacherDTO updatedEntityDTO = teacherService.update(teacherId, updatedTeacherDTO);
        return ResponseEntity.ok(updatedEntityDTO);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }
}

