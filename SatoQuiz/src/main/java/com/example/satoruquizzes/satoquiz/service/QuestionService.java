package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question getById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found for id: " + id));
    }

    public Question update(Long id, Question newQuestion) {
        Question existingQuestion = getById(id);
        existingQuestion.setAnswersNumber(newQuestion.getAnswersNumber());
        existingQuestion.setAnswersNumberCorrect(newQuestion.getAnswersNumberCorrect());
        existingQuestion.setText(newQuestion.getText());
        existingQuestion.setDuration(newQuestion.getDuration());
        existingQuestion.setType(newQuestion.getType());
        existingQuestion.setScorePoints(newQuestion.getScorePoints());
        existingQuestion.setSubject(newQuestion.getSubject());
        existingQuestion.setLevel(newQuestion.getLevel());

        return questionRepository.save(existingQuestion);
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}