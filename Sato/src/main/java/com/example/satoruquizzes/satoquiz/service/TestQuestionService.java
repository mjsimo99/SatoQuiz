package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TestDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TestQuestionDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.model.entity.Test;
import com.example.satoruquizzes.satoquiz.model.entity.TestQuestion;
import com.example.satoruquizzes.satoquiz.model.entity.TestQuestionId;
import com.example.satoruquizzes.satoquiz.repository.QuestionRepository;
import com.example.satoruquizzes.satoquiz.repository.TestQuestionRepository;
import com.example.satoruquizzes.satoquiz.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TestQuestionDTO save(TestQuestionDTO testQuestionDTO) {
        Test test = testRepository.findById(testQuestionDTO.getTest().getTestId())
                .orElseThrow(() -> new NotFoundException("Test not found for id: " + testQuestionDTO.getTest().getTestId()));

        Question question = questionRepository.findById(testQuestionDTO.getQuestion().getQuestionId())
                .orElseThrow(() -> new NotFoundException("Question not found for id: " + testQuestionDTO.getQuestion().getQuestionId()));

        TestQuestion testQuestion = modelMapper.map(testQuestionDTO, TestQuestion.class);
        testQuestion.setTest(test);
        testQuestion.setQuestion(question);

        testQuestion = testQuestionRepository.save(testQuestion);
        return modelMapper.map(testQuestion, TestQuestionDTO.class);
    }

    public TestQuestionDTO getTestQuestionById(Long testId, Long questionId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new NotFoundException("Test not found for id: " + testId));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found for id: " + questionId));

        TestQuestionId testQuestionId = new TestQuestionId(testId, questionId);
        TestQuestion testQuestion = testQuestionRepository.findById(testQuestionId)
                .orElseThrow(() -> new NotFoundException("TestQuestion not found for ids: " +
                        "testId=" + testId + ", questionId=" + questionId));

        TestQuestionDTO testQuestionDTO = modelMapper.map(testQuestion, TestQuestionDTO.class);
        testQuestionDTO.setTest(modelMapper.map(test, TestDTO.class));
        testQuestionDTO.setQuestion(modelMapper.map(question, QuestionDTO.class));

        return testQuestionDTO;
    }

    public List<TestQuestionDTO> getAllTestQuestions() {
        List<TestQuestion> testQuestions = testQuestionRepository.findAll();
        return testQuestions.stream()
                .map(testQuestion -> {
                    TestQuestionDTO testQuestionDTO = modelMapper.map(testQuestion, TestQuestionDTO.class);
                    testQuestionDTO.setTest(modelMapper.map(testQuestion.getTest(), TestDTO.class));
                    testQuestionDTO.setQuestion(modelMapper.map(testQuestion.getQuestion(), QuestionDTO.class));
                    return testQuestionDTO;
                })
                .collect(Collectors.toList());
    }
    public List<TestQuestionDTO> getTestQuestionsByTestId(Long testId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new NotFoundException("Test not found for id: " + testId));

        List<TestQuestion> testQuestions = testQuestionRepository.findByTestTestId(testId);

        return testQuestions.stream()
                .map(testQuestion -> {
                    TestQuestionDTO testQuestionDTO = modelMapper.map(testQuestion, TestQuestionDTO.class);
                    testQuestionDTO.setTest(modelMapper.map(test, TestDTO.class));
                    testQuestionDTO.setQuestion(modelMapper.map(testQuestion.getQuestion(), QuestionDTO.class));
                    return testQuestionDTO;
                })
                .collect(Collectors.toList());
    }
}