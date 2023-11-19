package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.TeacherDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Teacher;
import com.example.satoruquizzes.satoquiz.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TeacherDTO save(TeacherDTO teacherDTO) {
        Teacher teacher = modelMapper.map(teacherDTO, Teacher.class);
        teacher = teacherRepository.save(teacher);
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    public TeacherDTO getTeacherById(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    public TeacherDTO update(Long teacherId, TeacherDTO updatedTeacherDTO) {
        Optional<Teacher> existingTeacher = teacherRepository.findById(teacherId);

        if (existingTeacher.isPresent()) {
            Teacher teacherToUpdate = existingTeacher.get();
            teacherToUpdate.setSpecialty(updatedTeacherDTO.getSpecialty());

            teacherToUpdate = teacherRepository.save(teacherToUpdate);
            return modelMapper.map(teacherToUpdate, TeacherDTO.class);
        } else {
            throw new NotFoundException("Teacher not found for id: " + teacherId);
        }
    }

    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}