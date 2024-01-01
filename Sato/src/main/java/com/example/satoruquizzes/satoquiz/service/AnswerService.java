package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.CustomRuntimeException;
import com.example.satoruquizzes.satoquiz.exception.EntityNotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.AnswerDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.AnswerRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Answer;
import com.example.satoruquizzes.satoquiz.repository.AnswerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final ModelMapper modelMapper;

    public AnswerService(AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    public AnswerDTO save(AnswerDTO answerDTO) {
        try {
            Answer answer = modelMapper.map(answerDTO, Answer.class);
            answer = answerRepository.save(answer);
            return modelMapper.map(answer, AnswerDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while saving answer: " + e.getMessage());
        }
    }

    public List<AnswerRespDTO> getAllAnswers() {
        try {
            List<Answer> answers = answerRepository.findAll();
            return answers.stream()
                    .map(answer -> modelMapper.map(answer, AnswerRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching all answers: " + e.getMessage());
        }
    }

    public AnswerRespDTO getAnswerById(Long answerId) {
        try {
            Answer answer = answerRepository.findById(answerId).orElseThrow(() ->
                    new EntityNotFoundException("Answer not found for id: " + answerId));
            return modelMapper.map(answer, AnswerRespDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching answer by id: " + e.getMessage());
        }
    }

    public AnswerDTO update(Long answerId, AnswerDTO updatedAnswerDTO) {
        try {
            Optional<Answer> existingAnswer = answerRepository.findById(answerId);

            if (existingAnswer.isPresent()) {
                Answer answerToUpdate = existingAnswer.get();
                answerToUpdate.setAnswerText(updatedAnswerDTO.getAnswerText());

                answerToUpdate = answerRepository.save(answerToUpdate);
                return modelMapper.map(answerToUpdate, AnswerDTO.class);
            } else {
                throw new EntityNotFoundException("Answer not found for id: " + answerId);
            }
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while updating answer: " + e.getMessage());
        }
    }

    public void deleteAnswer(Long answerId) {
        try {
            answerRepository.deleteById(answerId);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while deleting answer: " + e.getMessage());
        }
    }
}