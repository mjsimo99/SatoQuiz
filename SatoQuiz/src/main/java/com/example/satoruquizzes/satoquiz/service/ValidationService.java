package com.example.satoruquizzes.satoquiz.service;


import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.ValidationDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Answer;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.model.entity.Validation;
import com.example.satoruquizzes.satoquiz.model.entity.ValidationId;
import com.example.satoruquizzes.satoquiz.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    public Validation save(ValidationDTO validationDTO) {
        Answer answer = answerService.getAnswerById(validationDTO.getAnswerId());
        Question question = questionService.getById(validationDTO.getQuestionId());

        Validation validation = new Validation();
        validation.setAnswer(answer);
        validation.setQuestion(question);
        validation.setPoints(validationDTO.getPoints());

        return validationRepository.save(validation);
    }

    public List<Validation> getAllValidations() {
        return validationRepository.findAll();
    }

    public Validation getValidationByIds(Long questionId, Long answerId) {
        ValidationId validationId = new ValidationId();
        validationId.setQuestion(questionId);
        validationId.setAnswer(answerId);

        return validationRepository.findById(validationId)
                .orElseThrow(() -> new NotFoundException("Validation not found for ids: " +
                        "questionId=" + questionId + ", answerId=" + answerId));
    }

}