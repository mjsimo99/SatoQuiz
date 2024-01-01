package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.CustomRuntimeException;
import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.ReponseDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ReponseRespDto;
import com.example.satoruquizzes.satoquiz.model.entity.Reponse;
import com.example.satoruquizzes.satoquiz.repository.ReponseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReponseService {

    private final ReponseRepository reponseRepository;

    private final ModelMapper modelMapper;

    public ReponseService(ReponseRepository reponseRepository, ModelMapper modelMapper) {
        this.reponseRepository = reponseRepository;
        this.modelMapper = modelMapper;
    }

    public ReponseDTO save(ReponseDTO reponseDTO) {
        try {
            Reponse reponse = modelMapper.map(reponseDTO, Reponse.class);
            reponse = reponseRepository.save(reponse);
            return modelMapper.map(reponse, ReponseDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while saving response: " + e.getMessage());
        }
    }

    public List<ReponseRespDto> getAllReponses() {
        try {
            List<Reponse> reponses = reponseRepository.findAll();
            return reponses.stream()
                    .map(reponse -> modelMapper.map(reponse, ReponseRespDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching all responses: " + e.getMessage());
        }
    }

    public ReponseRespDto getReponseById(Long reponseId) {
        try {
            Reponse reponse = reponseRepository.findById(reponseId).orElse(null);
            return modelMapper.map(reponse, ReponseRespDto.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching response by id: " + reponseId + ": " + e.getMessage());
        }
    }

    public ReponseDTO update(Long reponseId, ReponseDTO updatedReponseDTO) {
        try {
            Optional<Reponse> existingReponse = reponseRepository.findById(reponseId);

            if (existingReponse.isPresent()) {
                Reponse reponseToUpdate = existingReponse.get();
                reponseToUpdate.setQuestionResult(updatedReponseDTO.getQuestionResult());

                reponseToUpdate = reponseRepository.save(reponseToUpdate);
                return modelMapper.map(reponseToUpdate, ReponseDTO.class);
            } else {
                throw new NotFoundException("Response not found for id: " + reponseId);
            }
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while updating response: " + e.getMessage());
        }
    }

    public void deleteAllResponses(Long assignTestId) {
        try {
            reponseRepository.deleteByAssignTestId(assignTestId);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while deleting all responses for assignTestId: " + assignTestId + ": " + e.getMessage());
        }
    }

    public void deleteReponse(Long reponseId) {
        try {
            reponseRepository.deleteById(reponseId);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while deleting response: " + e.getMessage());
        }
    }
}
