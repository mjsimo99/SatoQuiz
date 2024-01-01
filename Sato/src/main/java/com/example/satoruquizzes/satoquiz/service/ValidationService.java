package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.ValidationDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ValidationRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Answer;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.model.entity.Validation;
import com.example.satoruquizzes.satoquiz.model.entity.ValidationId;
import com.example.satoruquizzes.satoquiz.repository.AnswerRepository;
import com.example.satoruquizzes.satoquiz.repository.QuestionRepository;
import com.example.satoruquizzes.satoquiz.repository.ValidationRepository;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidationService {

    private final ValidationRepository validationRepository;

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;

    public ValidationService(ValidationRepository validationRepository, AnswerRepository answerRepository, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.validationRepository = validationRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    public ValidationDTO save(ValidationDTO validationDTO) {
        try {
            Answer answer = answerRepository.findById(validationDTO.getAnswerId())
                    .orElseThrow(() -> new NotFoundException("Answer not found for id: " + validationDTO.getAnswerId()));

            Question question = questionRepository.findById(validationDTO.getQuestionId())
                    .orElseThrow(() -> new NotFoundException("Question not found for id: " + validationDTO.getQuestionId()));

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
        } catch (Exception e) {
            throw new NotFoundException("Error while saving Validation: " + e.getMessage());
        }
    }

    public ValidationRespDTO getValidationByIds(Long questionId, Long answerId) {
        try {
            ValidationId validationId = new ValidationId();
            validationId.setQuestion(questionId);
            validationId.setAnswer(answerId);

            return validationRepository.findById(validationId)
                    .map(validation -> modelMapper.map(validation, ValidationRespDTO.class))
                    .orElseThrow(() -> new NotFoundException("Validation not found for ids: " +
                            "questionId=" + questionId + ", answerId=" + answerId));
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching Validation by ids: " + e.getMessage());
        }
    }

    public List<ValidationRespDTO> getAllValidations() {
        try {
            List<Validation> validations = validationRepository.findAll();
            return validations.stream()
                    .map(validation -> modelMapper.map(validation, ValidationRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching all Validations: " + e.getMessage());
        }
    }

    public void delete(Long questionId, Long answerId) {
        try {
            ValidationId validationId = new ValidationId();
            validationId.setQuestion(questionId);
            validationId.setAnswer(answerId);

            if (validationRepository.existsById(validationId)) {
                validationRepository.deleteById(validationId);
            } else {
                throw new NotFoundException("Validation not found for ids: " +
                        "questionId=" + questionId + ", answerId=" + answerId);
            }
        } catch (Exception e) {
            throw new NotFoundException("Error while deleting Validation: " + e.getMessage());
        }
    }

    public ValidationDTO update(Long questionId, Long answerId, ValidationDTO newValidationDTO) {
        try {
            ValidationId validationId = new ValidationId();
            validationId.setQuestion(questionId);
            validationId.setAnswer(answerId);

            Validation existingValidation = validationRepository.findById(validationId)
                    .orElseThrow(() -> new NotFoundException("Validation not found for ids: " +
                            "questionId=" + questionId + ", answerId=" + answerId));

            Answer newAnswer = answerRepository.findById(newValidationDTO.getAnswerId())
                    .orElseThrow(() -> new NotFoundException("Answer not found for id: " + newValidationDTO.getAnswerId()));

            Question newQuestion = questionRepository.findById(newValidationDTO.getQuestionId())
                    .orElseThrow(() -> new NotFoundException("Question not found for id: " + newValidationDTO.getQuestionId()));

            existingValidation.setAnswer(newAnswer);
            existingValidation.setQuestion(newQuestion);
            existingValidation.setPoints(newValidationDTO.getPoints());

            Validation updatedValidation = validationRepository.save(existingValidation);
            return modelMapper.map(updatedValidation, ValidationDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while updating Validation: " + e.getMessage());
        }
    }
}
