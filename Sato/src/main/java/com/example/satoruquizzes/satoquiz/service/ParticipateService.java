package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.CustomRuntimeException;
import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.ParticipateDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ParticipateRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Participate;
import com.example.satoruquizzes.satoquiz.model.entity.Student;
import com.example.satoruquizzes.satoquiz.repository.ParticipateRepository;
import com.example.satoruquizzes.satoquiz.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipateService {

    private final ParticipateRepository participateRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public ParticipateService(ParticipateRepository participateRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.participateRepository = participateRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public ParticipateDTO saveParticipate(ParticipateDTO participateDTO) {
        try {
            Participate participate = modelMapper.map(participateDTO, Participate.class);

            Participate savedParticipate = participateRepository.save(participate);

            return modelMapper.map(savedParticipate, ParticipateDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while saving participate: " + e.getMessage());
        }
    }

    public ParticipateRespDTO getParticipateById(Long participateId) {
        try {
            Participate participate = participateRepository.findById(participateId)
                    .orElseThrow(() -> new NotFoundException("Participate not found for id: " + participateId));
            return modelMapper.map(participate, ParticipateRespDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching participate by id: " + participateId + ": " + e.getMessage());
        }
    }

    // Add other methods as needed with consistent exception handling
}
