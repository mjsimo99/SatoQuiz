package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.CustomRuntimeException;
import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.AssignTestDTO;
import com.example.satoruquizzes.satoquiz.model.entity.AssignTest;
import com.example.satoruquizzes.satoquiz.model.entity.Student;
import com.example.satoruquizzes.satoquiz.model.entity.Test;
import com.example.satoruquizzes.satoquiz.repository.AssignTestRepository;
import com.example.satoruquizzes.satoquiz.repository.StudentRepository;
import com.example.satoruquizzes.satoquiz.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignTestService {

    @Autowired
    private AssignTestRepository assignTestRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private StudentRepository studentRepository;

    public AssignTestDTO save(AssignTestDTO assignTestDTO) throws CustomRuntimeException {
        try {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime startDateTime = assignTestDTO.getStartDate();
            LocalDateTime endDateTime = assignTestDTO.getEndDate();

            if (startDateTime.isBefore(today) || startDateTime.getHour() < today.getHour() + 1) {
                throw new CustomRuntimeException("Start date should be after today and at least one hour in the future.");
            }

            if (endDateTime.isBefore(startDateTime)) {
                throw new CustomRuntimeException("End date should be after the start date.");
            }

            AssignTest assignTest = modelMapper.map(assignTestDTO, AssignTest.class);
            assignTest = assignTestRepository.save(assignTest);
            return modelMapper.map(assignTest, AssignTestDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new CustomRuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new CustomRuntimeException(e.getMessage());
        }
    }


    public List<AssignTestDTO> getAllAssignTests() {
        List<AssignTest> assignTests = assignTestRepository.findAll();
        return assignTests.stream()
                .map(assignTest -> modelMapper.map(assignTest, AssignTestDTO.class))
                .collect(Collectors.toList());
    }

    public AssignTestDTO getAssignTestById(Long assignTestId) {
        AssignTest assignTest = assignTestRepository.findById(assignTestId).orElse(null);
        return modelMapper.map(assignTest, AssignTestDTO.class);
    }

    public AssignTestDTO update(Long assignTestId, AssignTestDTO updatedAssignTestDTO) {
        Optional<AssignTest> existingAssignTest = assignTestRepository.findById(assignTestId);

        LocalDateTime startDateTime = updatedAssignTestDTO.getStartDate();
        LocalDateTime endDateTime = updatedAssignTestDTO.getEndDate();

        if (endDateTime.isBefore(startDateTime)) {
            throw new CustomRuntimeException("End date should be after the start date.");
        }

        if (existingAssignTest.isPresent()) {
            AssignTest assignTestToUpdate = existingAssignTest.get();
            assignTestToUpdate.setStartDate(updatedAssignTestDTO.getStartDate());
            assignTestToUpdate.setEndDate(updatedAssignTestDTO.getEndDate());
            assignTestToUpdate.setRaison(updatedAssignTestDTO.getRaison());
            assignTestToUpdate.setAttemptNumber(updatedAssignTestDTO.getAttemptNumber());
            assignTestToUpdate.setFinalResult(updatedAssignTestDTO.getFinalResult());

            Optional<Test> testOptional = testRepository.findById(updatedAssignTestDTO.getTest().getTestId());
            if (testOptional.isEmpty()) {
                throw new NotFoundException("Test not found for id: " + updatedAssignTestDTO.getTest().getTestId());
            }

            // Check if the associated Student exists
            Optional<Student> studentOptional = studentRepository.findById(updatedAssignTestDTO.getStudent().getStudentId());
            if (studentOptional.isEmpty()) {
                throw new NotFoundException("Student not found for id: " + updatedAssignTestDTO.getStudent().getStudentId());
            }

            assignTestToUpdate.setTest(testOptional.get());  // Set the associated Test
            assignTestToUpdate.setStudent(studentOptional.get());  // Set the associated Student



            assignTestToUpdate = assignTestRepository.save(assignTestToUpdate);
            return modelMapper.map(assignTestToUpdate, AssignTestDTO.class);
        } else {
            throw new NotFoundException("AssignTest not found for id: " + assignTestId);
        }
    }

    public void updateAttemptNumber(Long assignTestId, Integer newAttemptNumber) {
        Optional<AssignTest> existingAssignTest = assignTestRepository.findById(assignTestId);

        if (existingAssignTest.isPresent()) {
            AssignTest assignTestToUpdate = existingAssignTest.get();

            assignTestToUpdate.setAttemptNumber(newAttemptNumber);

            assignTestToUpdate.setFinalResult(0);

            assignTestRepository.save(assignTestToUpdate);
        } else {
            throw new NotFoundException("AssignTest not found for id: " + assignTestId);
        }
    }



    public void deleteAssignTest(Long assignTestId) {
        assignTestRepository.deleteById(assignTestId);
    }
}