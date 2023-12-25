package com.example.satoruquizzes.satoquiz;



import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.AnswerDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Answer;
import com.example.satoruquizzes.satoquiz.repository.AnswerRepository;
import com.example.satoruquizzes.satoquiz.service.AnswerService;
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
class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AnswerService answerService;

    @Test
    void testSaveAnswer() {
        AnswerDTO answerDTOToSave = new AnswerDTO();
        Answer answerToSave = new Answer();
        Answer savedAnswer = new Answer();
        AnswerDTO savedAnswerDTO = new AnswerDTO();

        Mockito.when(modelMapper.map(answerDTOToSave, Answer.class)).thenReturn(answerToSave);
        Mockito.when(answerRepository.save(answerToSave)).thenReturn(savedAnswer);
        Mockito.when(modelMapper.map(savedAnswer, AnswerDTO.class)).thenReturn(savedAnswerDTO);

        AnswerDTO result = answerService.save(answerDTOToSave);

        Mockito.verify(modelMapper, Mockito.times(1)).map(answerDTOToSave, Answer.class);
        Mockito.verify(answerRepository, Mockito.times(1)).save(answerToSave);
        Mockito.verify(modelMapper, Mockito.times(1)).map(savedAnswer, AnswerDTO.class);

        assertNotNull(result);
        assertEquals(savedAnswerDTO, result);
    }

    @Test
    void testGetAllAnswers() {
        List<Answer> answers = Arrays.asList(new Answer(), new Answer());
        List<AnswerDTO> expectedAnswerDTOs = Arrays.asList(new AnswerDTO(), new AnswerDTO());

        Mockito.when(answerRepository.findAll()).thenReturn(answers);
        Mockito.when(modelMapper.map(Mockito.any(Answer.class), Mockito.eq(AnswerDTO.class)))
                .thenReturn(expectedAnswerDTOs.get(0), expectedAnswerDTOs.get(1));

        List<AnswerDTO> result = answerService.getAllAnswers();

        Mockito.verify(answerRepository, Mockito.times(1)).findAll();
        Mockito.verify(modelMapper, Mockito.times(2)).map(Mockito.any(Answer.class), Mockito.eq(AnswerDTO.class));

        assertEquals(expectedAnswerDTOs, result);
    }

    @Test
    void testGetAnswerById() {
        Long answerId = 1L;
        Answer expectedAnswer = new Answer();
        AnswerDTO expectedAnswerDTO = new AnswerDTO();

        Mockito.when(answerRepository.findById(answerId)).thenReturn(Optional.of(expectedAnswer));
        Mockito.when(modelMapper.map(expectedAnswer, AnswerDTO.class)).thenReturn(expectedAnswerDTO);

        AnswerDTO result = answerService.getAnswerById(answerId);

        Mockito.verify(answerRepository, Mockito.times(1)).findById(answerId);
        Mockito.verify(modelMapper, Mockito.times(1)).map(expectedAnswer, AnswerDTO.class);

        assertNotNull(result);
        assertEquals(expectedAnswerDTO, result);
    }



    @Test
    void testUpdateAnswer() {
        Long answerId = 1L;
        AnswerDTO updatedAnswerDTO = new AnswerDTO();
        Answer existingAnswer = new Answer();
        Answer updatedAnswer = new Answer();
        AnswerDTO expectedUpdatedAnswerDTO = new AnswerDTO();

        Mockito.when(answerRepository.findById(answerId)).thenReturn(Optional.of(existingAnswer));
        Mockito.when(answerRepository.save(existingAnswer)).thenReturn(updatedAnswer);
        Mockito.when(modelMapper.map(updatedAnswer, AnswerDTO.class)).thenReturn(expectedUpdatedAnswerDTO);

        AnswerDTO result = answerService.update(answerId, updatedAnswerDTO);

        Mockito.verify(answerRepository, Mockito.times(1)).findById(answerId);
        Mockito.verify(answerRepository, Mockito.times(1)).save(existingAnswer);
        Mockito.verify(modelMapper, Mockito.times(1)).map(updatedAnswer, AnswerDTO.class);

        assertNotNull(result);
        assertEquals(expectedUpdatedAnswerDTO, result);
    }

    @Test
    void testUpdateAnswerNotFound() {
        Long answerId = 1L;
        AnswerDTO updatedAnswerDTO = new AnswerDTO();
        Mockito.when(answerRepository.findById(answerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> answerService.update(answerId, updatedAnswerDTO));
    }

    @Test
    void testDeleteAnswer() {
        Long answerId = 1L;
        answerService.deleteAnswer(answerId);

        Mockito.verify(answerRepository, Mockito.times(1)).deleteById(answerId);
    }
}