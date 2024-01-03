package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.CustomRuntimeException;
import com.example.satoruquizzes.satoquiz.exception.EntityNotFoundException;
import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.AssignTestDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.AssignTestRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.AssignTest;
import com.example.satoruquizzes.satoquiz.model.entity.Student;
import com.example.satoruquizzes.satoquiz.model.entity.Test;
import com.example.satoruquizzes.satoquiz.repository.AssignTestRepository;
import com.example.satoruquizzes.satoquiz.repository.StudentRepository;
import com.example.satoruquizzes.satoquiz.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignTestService {

    private final AssignTestRepository assignTestRepository;

    private final ModelMapper modelMapper;

    private final TestRepository testRepository;

    private final StudentRepository studentRepository;

    public AssignTestService(AssignTestRepository assignTestRepository, ModelMapper modelMapper, TestRepository testRepository, StudentRepository studentRepository) {
        this.assignTestRepository = assignTestRepository;
        this.modelMapper = modelMapper;
        this.testRepository = testRepository;
        this.studentRepository = studentRepository;
    }

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
            throw new CustomRuntimeException("Error while saving assignTest: " + e.getMessage());
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while saving assignTest: " + e.getMessage());
        }
    }

    public List<AssignTestRespDTO> getAllAssignTests() {
        try {
            List<AssignTest> assignTests = assignTestRepository.findAll();
            return assignTests.stream()
                    .map(assignTest -> modelMapper.map(assignTest, AssignTestRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching all assignTests: " + e.getMessage());
        }
    }

    public AssignTestRespDTO getAssignTestById(Long assignTestId) {
        try {
            AssignTest assignTest = assignTestRepository.findById(assignTestId).orElse(null);
            return modelMapper.map(assignTest, AssignTestRespDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching assignTest by id: " + e.getMessage());
        }
    }

    public AssignTestDTO update(Long assignTestId, AssignTestDTO updatedAssignTestDTO) {
        try {
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
                assignTestToUpdate.setReason(updatedAssignTestDTO.getReason());
                assignTestToUpdate.setAttemptNumber(updatedAssignTestDTO.getAttemptNumber());
                assignTestToUpdate.setFinalResult(updatedAssignTestDTO.getFinalResult());

                Optional<Test> testOptional = testRepository.findById(updatedAssignTestDTO.getTestId());
                if (testOptional.isEmpty()) {
                    throw new NotFoundException("Test not found for id: " + updatedAssignTestDTO.getTestId());
                }

                // Check if the associated Student exists
                Optional<Student> studentOptional = studentRepository.findById(updatedAssignTestDTO.getStudentId());
                if (studentOptional.isEmpty()) {
                    throw new NotFoundException("Student not found for id: " + updatedAssignTestDTO.getStudentId());
                }

                assignTestToUpdate.setTest(testOptional.get());
                assignTestToUpdate.setStudent(studentOptional.get());

                assignTestToUpdate = assignTestRepository.save(assignTestToUpdate);
                return modelMapper.map(assignTestToUpdate, AssignTestDTO.class);
            } else {
                throw new NotFoundException("AssignTest not found for id: " + assignTestId);
            }
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while updating assignTest: " + e.getMessage());
        }
    }

    public void updateAttemptNumber(Long assignTestId, Integer newAttemptNumber) {
        try {
            Optional<AssignTest> existingAssignTest = assignTestRepository.findById(assignTestId);

            if (existingAssignTest.isPresent()) {
                AssignTest assignTestToUpdate = existingAssignTest.get();
                assignTestToUpdate.setAttemptNumber(newAttemptNumber);
                assignTestToUpdate.setFinalResult((double) 0);
                assignTestRepository.save(assignTestToUpdate);
            } else {
                throw new NotFoundException("AssignTest not found for id: " + assignTestId);
            }
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while updating attempt number: " + e.getMessage());
        }
    }

    public void deleteAssignTest(Long assignTestId) {
        try {
            assignTestRepository.deleteById(assignTestId);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while deleting assignTest: " + e.getMessage());
        }
    }
}
