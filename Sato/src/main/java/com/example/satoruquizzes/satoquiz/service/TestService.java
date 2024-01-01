package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.TestDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.TestResDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Teacher;
import com.example.satoruquizzes.satoquiz.model.entity.Test;
import com.example.satoruquizzes.satoquiz.repository.TeacherRepository;
import com.example.satoruquizzes.satoquiz.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService {

    private final TestRepository testRepository;

    private final TeacherRepository teacherRepository;

    private final ModelMapper modelMapper;

    public TestService(TestRepository testRepository, TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.testRepository = testRepository;
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public TestDTO save(TestDTO testDTO) {
        try {
            Test test = modelMapper.map(testDTO, Test.class);
            test = testRepository.save(test);
            return modelMapper.map(test, TestDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while saving Test: " + e.getMessage());
        }
    }

    public List<TestResDTO> getAllTests() {
        try {
            List<Test> tests = testRepository.findAll();
            return tests.stream()
                    .map(test -> modelMapper.map(test, TestResDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching all Tests: " + e.getMessage());
        }
    }

    public TestResDTO getTestById(Long testId) {
        try {
            Test test = testRepository.findById(testId)
                    .orElseThrow(() -> new NotFoundException("Test not found for id: " + testId));
            return modelMapper.map(test, TestResDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching Test by id: " + e.getMessage());
        }
    }

    public TestDTO update(Long testId, TestDTO updatedTestDTO) {
        try {
            Optional<Test> existingTest = testRepository.findById(testId);

            if (existingTest.isPresent()) {
                Test testToUpdate = existingTest.get();
                testToUpdate.setSuccessScore(updatedTestDTO.getSuccessScore());
                testToUpdate.setViewAnswer(updatedTestDTO.getViewAnswer());
                testToUpdate.setViewResult(updatedTestDTO.getViewResult());
                testToUpdate.setMaxAttempt(updatedTestDTO.getMaxAttempt());
                testToUpdate.setRemark(updatedTestDTO.getRemark());
                testToUpdate.setInstructions(updatedTestDTO.getInstructions());

                // Update teacher also
                Teacher teacher = teacherRepository.findById(updatedTestDTO.getTeacherId())
                        .orElseThrow(() -> new NotFoundException("Teacher not found for id: " + updatedTestDTO.getTeacherId()));
                testToUpdate.setTeacher(teacher);

                testToUpdate = testRepository.save(testToUpdate);
                return modelMapper.map(testToUpdate, TestDTO.class);
            } else {
                throw new NotFoundException("Test not found for id: " + testId);
            }
        } catch (Exception e) {
            throw new NotFoundException("Error while updating Test: " + e.getMessage());
        }
    }

    public void deleteTest(Long testId) {
        try {
            testRepository.deleteById(testId);
        } catch (Exception e) {
            throw new NotFoundException("Error while deleting Test: " + e.getMessage());
        }
    }
}
