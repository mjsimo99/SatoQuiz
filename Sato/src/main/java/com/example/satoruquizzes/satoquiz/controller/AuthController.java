package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.StudentDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TeacherDTO;
import com.example.satoruquizzes.satoquiz.model.dto.UserDTO;
import com.example.satoruquizzes.satoquiz.service.AuthService;
import com.example.satoruquizzes.satoquiz.service.StudentService;
import com.example.satoruquizzes.satoquiz.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    /*
        private final AuthService authService;
        private final StudentService studentService;

        private final TeacherService teacherService;

        private final ModelMapper modelMapper;

        public AuthController(AuthService authService,StudentService studentService,TeacherService teacherService,ModelMapper modelMapper) {
            this.authService = authService;
            this.studentService = studentService;
            this.teacherService = teacherService;
            this.modelMapper = modelMapper;
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
            UserDTO authenticatedUser = authService.authenticate(userDTO);

            if (authenticatedUser != null && authenticatedUser.getPassword().equals(userDTO.getPassword())) {
                // Authentication successful
                return ResponseEntity.ok("Login successful");
            } else {
                // Authentication failed
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
            if ("student".equals(userDTO.getUserType())) {
                StudentDTO studentDTO = modelMapper.map(userDTO, StudentDTO.class);
                // Log values for debugging
                System.out.println("userDTO: " + userDTO);
                System.out.println("studentDTO: " + studentDTO);
                studentService.save(studentDTO);
            } else if ("teacher".equals(userDTO.getUserType())) {
                TeacherDTO teacherDTO = modelMapper.map(userDTO, TeacherDTO.class);
                // Log values for debugging
                System.out.println("userDTO: " + userDTO);
                System.out.println("teacherDTO: " + teacherDTO);
                teacherService.save(teacherDTO);
            } else {
                return ResponseEntity.badRequest().body("Invalid userType");
            }

            authService.addUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }

    }


     */
    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final StudentService studentService;
    private final TeacherService teacherService;


    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper, StudentService studentService, TeacherService teacherService) {
        this.authService = authService;
        this.modelMapper = modelMapper;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @PostMapping("/register/student")
    public ResponseEntity<String> registerStudent(@RequestBody StudentDTO studentDTO) {
        // Save the student in the database using the StudentService
        studentService.save(studentDTO);

        // Also, add the student to the authentication service
        authService.addStudent(studentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully");
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<String> registerTeacher(@RequestBody TeacherDTO teacherDTO) {

        teacherService.save(teacherDTO);
        authService.addTeacher(teacherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Teacher registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody StudentDTO studentDTO) {
        // Assuming login is specific to students in this example
        StudentDTO authenticatedStudent = (StudentDTO) authService.authenticate(studentDTO.getLastName(), "student");

        if (authenticatedStudent != null && authenticatedStudent.getPassword().equals(studentDTO.getPassword())) {
            // Authentication successful
            return ResponseEntity.ok("Login successful");
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}