package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.AssignTestDTO;
import com.example.satoruquizzes.satoquiz.model.entity.AssignTest;
import com.example.satoruquizzes.satoquiz.repository.AssignTestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignTestService {

    @Autowired
    private AssignTestRepository assignTestRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AssignTestDTO save(AssignTestDTO assignTestDTO) {
        AssignTest assignTest = modelMapper.map(assignTestDTO, AssignTest.class);
        assignTest = assignTestRepository.save(assignTest);
        return modelMapper.map(assignTest, AssignTestDTO.class);
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

        if (existingAssignTest.isPresent()) {
            AssignTest assignTestToUpdate = existingAssignTest.get();
            assignTestToUpdate.setStartDate(updatedAssignTestDTO.getStartDate());
            assignTestToUpdate.setEndDate(updatedAssignTestDTO.getEndDate());
            assignTestToUpdate.setRaison(updatedAssignTestDTO.getRaison());
            assignTestToUpdate.setScore(updatedAssignTestDTO.getScore());
            assignTestToUpdate.setAttemptNumber(updatedAssignTestDTO.getAttemptNumber());
            assignTestToUpdate.setFinalResult(updatedAssignTestDTO.getFinalResult());


            assignTestToUpdate = assignTestRepository.save(assignTestToUpdate);
            return modelMapper.map(assignTestToUpdate, AssignTestDTO.class);
        } else {
            throw new NotFoundException("AssignTest not found for id: " + assignTestId);
        }
    }

    public void deleteAssignTest(Long assignTestId) {
        assignTestRepository.deleteById(assignTestId);
    }
}