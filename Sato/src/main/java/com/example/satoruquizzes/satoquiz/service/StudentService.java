package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.StudentDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Student;
import com.example.satoruquizzes.satoquiz.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentDTO save(StudentDTO studentDTO) {
        try {
            Student student = modelMapper.map(studentDTO, Student.class);
            student = studentRepository.save(student);
            return modelMapper.map(student, StudentDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while saving student: " + e.getMessage());
        }
    }

    public List<StudentDTO> getAllStudents() {
        try {
            List<Student> students = studentRepository.findAll();
            return students.stream()
                    .map(student -> modelMapper.map(student, StudentDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching all students: " + e.getMessage());
        }
    }

    public StudentDTO getStudentById(Long studentId) {
        try {
            Student student = studentRepository.findById(studentId).orElse(null);
            return modelMapper.map(student, StudentDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching student with id: " + studentId + ": " + e.getMessage());
        }
    }

    public StudentDTO update(Long studentId, StudentDTO updatedStudentDTO) {
        try {
            Optional<Student> existingStudent = studentRepository.findById(studentId);

            if (existingStudent.isPresent()) {
                Student studentToUpdate = existingStudent.get();
                studentToUpdate.setRegistrationDate(updatedStudentDTO.getRegistrationDate());
                studentToUpdate.setFirstName(updatedStudentDTO.getFirstName());
                studentToUpdate.setLastName(updatedStudentDTO.getLastName());
                studentToUpdate.setDateOfBirth(updatedStudentDTO.getDateOfBirth());
                studentToUpdate.setAddress(updatedStudentDTO.getAddress());

                studentToUpdate = studentRepository.save(studentToUpdate);
                return modelMapper.map(studentToUpdate, StudentDTO.class);
            } else {
                throw new NotFoundException("Student not found for id: " + studentId);
            }
        } catch (Exception e) {
            throw new NotFoundException("Error while updating student with id: " + studentId + ": " + e.getMessage());
        }
    }

    public void deleteStudent(Long studentId) {
        try {
            studentRepository.deleteById(studentId);
        } catch (Exception e) {
            throw new NotFoundException("Error while deleting student with id: " + studentId + ": " + e.getMessage());
        }
    }
}
