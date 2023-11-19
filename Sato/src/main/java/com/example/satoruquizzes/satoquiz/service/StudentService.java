package com.example.satoruquizzes.satoquiz.service;


import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.StudentDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Student;
import com.example.satoruquizzes.satoquiz.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public StudentDTO save(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentDTO update(Long studentId, StudentDTO updatedStudentDTO) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);

        if (existingStudent.isPresent()) {
            Student studentToUpdate = existingStudent.get();
            studentToUpdate.setRegistrationDate(updatedStudentDTO.getRegistrationDate());

            studentToUpdate = studentRepository.save(studentToUpdate);
            return modelMapper.map(studentToUpdate, StudentDTO.class);
        } else {
            throw new NotFoundException("Student not found for id: " + studentId);
        }
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}