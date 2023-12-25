package com.example.satoruquizzes.satoquiz;


import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.MediaDTO;
import com.example.satoruquizzes.satoquiz.model.dto.QuestionDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Media;
import com.example.satoruquizzes.satoquiz.model.entity.Question;
import com.example.satoruquizzes.satoquiz.repository.MediaRepository;
import com.example.satoruquizzes.satoquiz.repository.QuestionRepository;
import com.example.satoruquizzes.satoquiz.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private QuestionService questionService;

    @Test
    void testSaveQuestion() {
        QuestionDTO questionDTOToSave = new QuestionDTO();
        Question questionToSave = new Question();
        Question savedQuestion = new Question();
        QuestionDTO savedQuestionDTO = new QuestionDTO();

        Mockito.when(modelMapper.map(questionDTOToSave, Question.class)).thenReturn(questionToSave);
        Mockito.when(questionRepository.save(questionToSave)).thenReturn(savedQuestion);
        Mockito.when(modelMapper.map(savedQuestion, QuestionDTO.class)).thenReturn(savedQuestionDTO);

        QuestionDTO result = questionService.save(questionDTOToSave);

        Mockito.verify(modelMapper, Mockito.times(1)).map(questionDTOToSave, Question.class);
        Mockito.verify(questionRepository, Mockito.times(1)).save(questionToSave);
        Mockito.verify(modelMapper, Mockito.times(1)).map(savedQuestion, QuestionDTO.class);

        assertNotNull(result);
        assertEquals(savedQuestionDTO, result);
    }



    @Test
    void testGetAllQuestions() {
        List<Question> questions = Arrays.asList(new Question(), new Question());
        List<QuestionDTO> expectedQuestionDTOs = Arrays.asList(new QuestionDTO(), new QuestionDTO());

        Mockito.when(questionRepository.findAll()).thenReturn(questions);
        Mockito.when(modelMapper.map(Mockito.any(Question.class), Mockito.eq(QuestionDTO.class)))
                .thenReturn(expectedQuestionDTOs.get(0), expectedQuestionDTOs.get(1));

        List<QuestionDTO> result = questionService.getAll();

        Mockito.verify(questionRepository, Mockito.times(1)).findAll();
        Mockito.verify(modelMapper, Mockito.times(2)).map(Mockito.any(Question.class), Mockito.eq(QuestionDTO.class));

        assertEquals(expectedQuestionDTOs, result);
    }

    @Test
    void testGetQuestionById() {
        Long questionId = 1L;
        Question expectedQuestion = new Question();
        QuestionDTO expectedQuestionDTO = new QuestionDTO();

        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(expectedQuestion));
        Mockito.when(modelMapper.map(expectedQuestion, QuestionDTO.class)).thenReturn(expectedQuestionDTO);

        QuestionDTO result = questionService.getById(questionId);

        Mockito.verify(questionRepository, Mockito.times(1)).findById(questionId);
        Mockito.verify(modelMapper, Mockito.times(1)).map(expectedQuestion, QuestionDTO.class);

        assertNotNull(result);
        assertEquals(expectedQuestionDTO, result);
    }

    @Test
    void testGetQuestionByIdNotFound() {
        Long questionId = 1L;
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> questionService.getById(questionId));
    }

    @Test
    void testUpdateQuestion() {
        Long questionId = 1L;
        QuestionDTO updatedQuestionDTO = new QuestionDTO();
        Question existingQuestion = new Question();
        Question updatedQuestion = new Question();
        QuestionDTO expectedUpdatedQuestionDTO = new QuestionDTO();

        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));
        Mockito.when(questionRepository.save(existingQuestion)).thenReturn(updatedQuestion);
        Mockito.when(modelMapper.map(updatedQuestion, QuestionDTO.class)).thenReturn(expectedUpdatedQuestionDTO);

        QuestionDTO result = questionService.update(questionId, updatedQuestionDTO);

        Mockito.verify(questionRepository, Mockito.times(1)).findById(questionId);
        Mockito.verify(questionRepository, Mockito.times(1)).save(existingQuestion);
        Mockito.verify(modelMapper, Mockito.times(1)).map(updatedQuestion, QuestionDTO.class);

        assertNotNull(result);
        assertEquals(expectedUpdatedQuestionDTO, result);
    }

    @Test
    void testUpdateQuestionNotFound() {
        Long questionId = 1L;
        QuestionDTO updatedQuestionDTO = new QuestionDTO();
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> questionService.update(questionId, updatedQuestionDTO));
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;
        questionService.delete(questionId);

        Mockito.verify(questionRepository, Mockito.times(1)).deleteById(questionId);
    }
}