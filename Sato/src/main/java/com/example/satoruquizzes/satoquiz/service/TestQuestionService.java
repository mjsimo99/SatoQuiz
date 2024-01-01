package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TestDTO;
import com.example.satoruquizzes.satoquiz.model.dto.TestQuestionDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.TestQuestionRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.model.entity.Test;
import com.example.satoruquizzes.satoquiz.model.entity.TestQuestion;
import com.example.satoruquizzes.satoquiz.model.entity.TestQuestionId;
import com.example.satoruquizzes.satoquiz.repository.QuestionRepository;
import com.example.satoruquizzes.satoquiz.repository.TestQuestionRepository;
import com.example.satoruquizzes.satoquiz.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestQuestionService {

    private final TestQuestionRepository testQuestionRepository;

    private final TestRepository testRepository;

    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;

    public TestQuestionService(TestQuestionRepository testQuestionRepository, TestRepository testRepository, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.testQuestionRepository = testQuestionRepository;
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    public TestQuestionDTO save(TestQuestionDTO testQuestionDTO) {
        try {
            Test test = testRepository.findById(testQuestionDTO.getTestId())
                    .orElseThrow(() -> new NotFoundException("Test not found for id: " + testQuestionDTO.getTestId()));

            Question question = questionRepository.findById(testQuestionDTO.getQuestionId())
                    .orElseThrow(() -> new NotFoundException("Question not found for id: " + testQuestionDTO.getQuestionId()));

            TestQuestion testQuestion = modelMapper.map(testQuestionDTO, TestQuestion.class);
            testQuestion.setTest(test);
            testQuestion.setQuestion(question);

            testQuestion = testQuestionRepository.save(testQuestion);
            return modelMapper.map(testQuestion, TestQuestionDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while saving TestQuestion: " + e.getMessage());
        }
    }

    public TestQuestionRespDTO getTestQuestionById(Long testId, Long questionId) {
        try {
            Test test = testRepository.findById(testId)
                    .orElseThrow(() -> new NotFoundException("Test not found for id: " + testId));

            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new NotFoundException("Question not found for id: " + questionId));

            TestQuestionId testQuestionId = new TestQuestionId(testId, questionId);
            TestQuestion testQuestion = testQuestionRepository.findById(testQuestionId)
                    .orElseThrow(() -> new NotFoundException("TestQuestion not found for ids: " +
                            "testId=" + testId + ", questionId=" + questionId));

            TestQuestionRespDTO testQuestionRespDTO = modelMapper.map(testQuestion, TestQuestionRespDTO.class);
            testQuestionRespDTO.setTest(modelMapper.map(test, TestDTO.class));
            testQuestionRespDTO.setQuestion(modelMapper.map(question, QuestionDTO.class));

            return testQuestionRespDTO;
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching TestQuestion: " + e.getMessage());
        }
    }

    public List<TestQuestionRespDTO> getAllTestQuestions() {
        try {
            List<TestQuestion> testQuestions = testQuestionRepository.findAll();
            return testQuestions.stream()
                    .map(testQuestion -> {
                        TestQuestionRespDTO testQuestionRespDTO = modelMapper.map(testQuestion, TestQuestionRespDTO.class);
                        testQuestionRespDTO.setTest(modelMapper.map(testQuestion.getTest(), TestDTO.class));
                        testQuestionRespDTO.setQuestion(modelMapper.map(testQuestion.getQuestion(), QuestionDTO.class));
                        return testQuestionRespDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching all TestQuestions: " + e.getMessage());
        }
    }

    public List<TestQuestionRespDTO> getTestQuestionsByTestId(Long testId) {
        try {
            Test test = testRepository.findById(testId)
                    .orElseThrow(() -> new NotFoundException("Test not found for id: " + testId));

            List<TestQuestion> testQuestions = testQuestionRepository.findByTestTestId(testId);

            return testQuestions.stream()
                    .map(testQuestion -> {
                        TestQuestionRespDTO testQuestionRespDTO = modelMapper.map(testQuestion, TestQuestionRespDTO.class);
                        testQuestionRespDTO.setTest(modelMapper.map(test, TestDTO.class));
                        testQuestionRespDTO.setQuestion(modelMapper.map(testQuestion.getQuestion(), QuestionDTO.class));
                        return testQuestionRespDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching TestQuestions by TestId: " + e.getMessage());
        }
    }
}
