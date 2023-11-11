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

    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Question existingQuestion = getQuestionById(questionId);
        existingQuestion.setAnswersNumber(updatedQuestion.getAnswersNumber());
        existingQuestion.setAnswersNumberCorrect(updatedQuestion.getAnswersNumberCorrect());
        existingQuestion.setText(updatedQuestion.getText());
        existingQuestion.setDuration(updatedQuestion.getDuration());
        existingQuestion.setType(updatedQuestion.getType());
        existingQuestion.setScorePoints(updatedQuestion.getScorePoints());
        existingQuestion.setSubject(updatedQuestion.getSubject());
        existingQuestion.setLevel(updatedQuestion.getLevel());
        return save(existingQuestion);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found for ID: " + questionId));
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}