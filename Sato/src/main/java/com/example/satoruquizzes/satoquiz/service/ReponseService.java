package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.ReponseDTO;
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

    @Autowired
    private ReponseRepository reponseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ReponseDTO save(ReponseDTO reponseDTO) {
        Reponse reponse = modelMapper.map(reponseDTO, Reponse.class);
        reponse = reponseRepository.save(reponse);
        return modelMapper.map(reponse, ReponseDTO.class);
    }

    public List<ReponseDTO> getAllReponses() {
        List<Reponse> reponses = reponseRepository.findAll();
        return reponses.stream()
                .map(reponse -> modelMapper.map(reponse, ReponseDTO.class))
                .collect(Collectors.toList());
    }

    public ReponseDTO getReponseById(Long reponseId) {
        Reponse reponse = reponseRepository.findById(reponseId).orElse(null);
        return modelMapper.map(reponse, ReponseDTO.class);
    }

    public ReponseDTO update(Long reponseId, ReponseDTO updatedReponseDTO) {
        Optional<Reponse> existingReponse = reponseRepository.findById(reponseId);

        if (existingReponse.isPresent()) {
            Reponse reponseToUpdate = existingReponse.get();
            reponseToUpdate.setQuestionResult(updatedReponseDTO.getQuestionResult());

            reponseToUpdate = reponseRepository.save(reponseToUpdate);
            return modelMapper.map(reponseToUpdate, ReponseDTO.class);
        } else {
            throw new NotFoundException("Reponse not found for id: " + reponseId);
        }
    }

    public void deleteReponse(Long reponseId) {
        reponseRepository.deleteById(reponseId);
    }
}