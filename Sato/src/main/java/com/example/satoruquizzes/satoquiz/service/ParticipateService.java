package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.EntityNotFoundException;
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

    @Autowired
    public ParticipateService(ParticipateRepository participateRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.participateRepository = participateRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }





    public ParticipateDTO saveParticipate(ParticipateDTO participateDTO) {
        Participate participate = modelMapper.map(participateDTO, Participate.class);

        Participate savedParticipate = participateRepository.save(participate);

        return modelMapper.map(savedParticipate, ParticipateDTO.class);
    }

}
