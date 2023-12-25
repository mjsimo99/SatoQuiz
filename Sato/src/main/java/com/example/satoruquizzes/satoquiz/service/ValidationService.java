package com.example.satoruquizzes.satoquiz.service;


import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.AnswerDTO;
import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import com.example.satoruquizzes.satoquiz.model.dto.ValidationDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Answer;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.model.entity.Validation;
import com.example.satoruquizzes.satoquiz.model.entity.ValidationId;
import com.example.satoruquizzes.satoquiz.repository.AnswerRepository;
import com.example.satoruquizzes.satoquiz.repository.QuestionRepository;
import com.example.satoruquizzes.satoquiz.repository.ValidationRepository;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidationService {

    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ValidationDTO save(ValidationDTO validationDTO) {
        Answer answer = answerRepository.findById(validationDTO.getAnswer().getAnswerId())
                .orElseThrow(() -> new NotFoundException("Answer not found for id: " + validationDTO.getAnswer().getAnswerId()));

        Question question = questionRepository.findById(validationDTO.getQuestion().getQuestionId())
                .orElseThrow(() -> new NotFoundException("Question not found for id: " + validationDTO.getQuestion().getQuestionId()));

        ValidationId validationId = new ValidationId();
        validationId.setQuestion(question.getQuestionId());
        validationId.setAnswer(answer.getAnswerId());

        if (validationRepository.existsById(validationId)) {
            throw new ValidationException("Validation already exists for questionId=" + question.getQuestionId() +
                    ", answerId=" + answer.getAnswerId());
        }

        Validation validation = modelMapper.map(validationDTO, Validation.class);
        validation.setAnswer(answer);
        validation.setQuestion(question);

        validation = validationRepository.save(validation);
        return modelMapper.map(validation, ValidationDTO.class);
    }

    public ValidationDTO getValidationByIds(Long questionId, Long answerId) {
        ValidationId validationId = new ValidationId();
        validationId.setQuestion(questionId);
        validationId.setAnswer(answerId);

        return validationRepository.findById(validationId)
                .map(validation -> modelMapper.map(validation, ValidationDTO.class))
                .orElseThrow(() -> new NotFoundException("Validation not found for ids: " +
                        "questionId=" + questionId + ", answerId=" + answerId));
    }


    public List<ValidationDTO> getAllValidations() {
        List<Validation> validations = validationRepository.findAll();
        return validations.stream()
                .map(validation -> modelMapper.map(validation, ValidationDTO.class))
                .collect(Collectors.toList());
    }

    public void delete(Long questionId, Long answerId) {
        ValidationId validationId = new ValidationId();
        validationId.setQuestion(questionId);
        validationId.setAnswer(answerId);

        if (validationRepository.existsById(validationId)) {
            validationRepository.deleteById(validationId);
        } else {
            throw new NotFoundException("Validation not found for ids: " +
                    "questionId=" + questionId + ", answerId=" + answerId);
        }
    }
    public ValidationDTO update(Long questionId, Long answerId, ValidationDTO newValidationDTO) {
        ValidationId validationId = new ValidationId();
        validationId.setQuestion(questionId);
        validationId.setAnswer(answerId);

        Validation existingValidation = validationRepository.findById(validationId)
                .orElseThrow(() -> new NotFoundException("Validation not found for ids: " +
                        "questionId=" + questionId + ", answerId=" + answerId));

        Answer newAnswer = answerRepository.findById(newValidationDTO.getAnswer().getAnswerId())
                .orElseThrow(() -> new NotFoundException("Answer not found for id: " + newValidationDTO.getAnswer().getAnswerId()));

        Question newQuestion = questionRepository.findById(newValidationDTO.getQuestion().getQuestionId())
                .orElseThrow(() -> new NotFoundException("Question not found for id: " + newValidationDTO.getQuestion().getQuestionId()));

        existingValidation.setAnswer(newAnswer);
        existingValidation.setQuestion(newQuestion);
        existingValidation.setPoints(newValidationDTO.getPoints());

        Validation updatedValidation = validationRepository.save(existingValidation);
        return modelMapper.map(updatedValidation, ValidationDTO.class);
    }
}

