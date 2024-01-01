package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.TeacherDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Teacher;
import com.example.satoruquizzes.satoquiz.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final ModelMapper modelMapper;

    public TeacherService(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public TeacherDTO save(TeacherDTO teacherDTO) {
        try {
            Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
            teacher = teacherRepository.save(teacher);
            return modelMapper.map(teacher, TeacherDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while saving teacher: " + e.getMessage());
        }
    }

    public List<TeacherDTO> getAllTeachers() {
        try {
            List<Teacher> teachers = teacherRepository.findAll();
            return teachers.stream()
                    .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching all teachers: " + e.getMessage());
        }
    }

    public TeacherDTO getTeacherById(Long teacherId) {
        try {
            Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
            return modelMapper.map(teacher, TeacherDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching teacher with ID: " + teacherId + ": " + e.getMessage());
        }
    }

    public TeacherDTO update(Long teacherId, TeacherDTO updatedTeacherDTO) {
        try {
            Optional<Teacher> existingTeacher = teacherRepository.findById(teacherId);

            if (existingTeacher.isPresent()) {
                Teacher teacherToUpdate = existingTeacher.get();
                teacherToUpdate.setSpecialty(updatedTeacherDTO.getSpecialty());
                teacherToUpdate.setFirstName(updatedTeacherDTO.getFirstName());
                teacherToUpdate.setLastName(updatedTeacherDTO.getLastName());
                teacherToUpdate.setDateOfBirth(updatedTeacherDTO.getDateOfBirth());
                teacherToUpdate.setAddress(updatedTeacherDTO.getAddress());

                teacherToUpdate = teacherRepository.save(teacherToUpdate);
                return modelMapper.map(teacherToUpdate, TeacherDTO.class);
            } else {
                throw new NotFoundException("Teacher not found for id: " + teacherId);
            }
        } catch (Exception e) {
            throw new NotFoundException("Error while updating teacher with ID: " + teacherId + ": " + e.getMessage());
        }
    }

    public void deleteTeacher(Long teacherId) {
        try {
            teacherRepository.deleteById(teacherId);
        } catch (Exception e) {
            throw new NotFoundException("Error while deleting teacher with ID: " + teacherId + ": " + e.getMessage());
        }
    }
}
