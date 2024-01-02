package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.model.dto.StudentDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TeacherDTO;
import com.example.satoruquizzes.satoquiz.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

private final Map<String, StudentDTO> studentCredentials = new HashMap<>();
    private final Map<String, TeacherDTO> teacherCredentials = new HashMap<>();

    public void addStudent(StudentDTO studentDTO) {
        studentCredentials.put(studentDTO.getLastName(), studentDTO);
    }

    public void addTeacher(TeacherDTO teacherDTO) {
        teacherCredentials.put(teacherDTO.getLastName(), teacherDTO);
    }

    public Object authenticate(String lastName, String userType) {
        if ("student".equals(userType)) {
            return studentCredentials.get(lastName);
        } else if ("teacher".equals(userType)) {
            return teacherCredentials.get(lastName);
        } else {
            // Handle other user types or raise an exception if needed
            throw new IllegalArgumentException("Invalid userType");
        }
    }

}
