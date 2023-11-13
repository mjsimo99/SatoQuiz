package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.entity.Answer;
import com.example.satoruquizzes.satoquiz.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }
    public Answer update(Long answerId, Answer updatedAnswer) {
        Optional<Answer> existingAnswer = answerRepository.findById(answerId);

        if (existingAnswer.isPresent()) {
            Answer answerToUpdate = existingAnswer.get();
            answerToUpdate.setAnswerText(updatedAnswer.getAnswerText());
            answerToUpdate.setValidations(updatedAnswer.getValidations());

            return answerRepository.save(answerToUpdate);
        } else {
            throw new NotFoundException("Answer not found for id: " + answerId);
        }
    }
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}