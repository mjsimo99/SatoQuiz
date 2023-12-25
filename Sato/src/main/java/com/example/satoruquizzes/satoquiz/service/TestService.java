package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.TestDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Teacher;
import com.example.satoruquizzes.satoquiz.model.entity.Test;
import com.example.satoruquizzes.satoquiz.repository.TeacherRepository;
import com.example.satoruquizzes.satoquiz.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TestDTO save(TestDTO testDTO) {
        Test test = modelMapper.map(testDTO, Test.class);
        test = testRepository.save(test);
        return modelMapper.map(test, TestDTO.class);
    }

    public List<TestDTO> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return tests.stream()
                .map(test -> modelMapper.map(test, TestDTO.class))
                .collect(Collectors.toList());
    }

    public TestDTO getTestById(Long testId) {
        Test test = testRepository.findById(testId).orElse(null);
        return modelMapper.map(test, TestDTO.class);
    }

    public TestDTO update(Long testId, TestDTO updatedTestDTO) {
        Optional<Test> existingTest = testRepository.findById(testId);

        if (existingTest.isPresent()) {
            Test testToUpdate = existingTest.get();
            testToUpdate.setSuccessScore(updatedTestDTO.getSuccessScore());
            testToUpdate.setViewAnswer(updatedTestDTO.getViewAnswer());
            testToUpdate.setViewResult(updatedTestDTO.getViewResult());
            testToUpdate.setMaxAttempt(updatedTestDTO.getMaxAttempt());
            testToUpdate.setRemark(updatedTestDTO.getRemark());
            testToUpdate.setInstructions(updatedTestDTO.getInstructions());

            //update teachar also

            Teacher teacher = teacherRepository.findById(updatedTestDTO.getTeacher().getTeacherId())
                    .orElseThrow(() -> new NotFoundException("Teacher not found for id: " + updatedTestDTO.getTeacher().getTeacherId()));
            testToUpdate.setTeacher(teacher);


            testToUpdate = testRepository.save(testToUpdate);
            return modelMapper.map(testToUpdate, TestDTO.class);
        } else {
            throw new NotFoundException("Test not found for id: " + testId);
        }
    }

    public void deleteTest(Long testId) {
        testRepository.deleteById(testId);
    }
}