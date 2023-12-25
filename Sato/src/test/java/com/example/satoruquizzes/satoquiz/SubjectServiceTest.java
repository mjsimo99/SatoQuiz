package com.example.satoruquizzes.satoquiz;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.SubjectDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Subject;
import com.example.satoruquizzes.satoquiz.repository.SubjectRepository;
import com.example.satoruquizzes.satoquiz.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SubjectService subjectService;

    @Test
    void testSaveSubject() {
        SubjectDTO subjectDTOToSave = new SubjectDTO();
        Subject subjectToSave = new Subject();
        Subject savedSubject = new Subject();
        SubjectDTO savedSubjectDTO = new SubjectDTO();

        Mockito.when(modelMapper.map(subjectDTOToSave, Subject.class)).thenReturn(subjectToSave);
        Mockito.when(subjectRepository.save(subjectToSave)).thenReturn(savedSubject);
        Mockito.when(modelMapper.map(savedSubject, SubjectDTO.class)).thenReturn(savedSubjectDTO);

        SubjectDTO result = subjectService.save(subjectDTOToSave);

        Mockito.verify(modelMapper, Mockito.times(1)).map(subjectDTOToSave, Subject.class);
        Mockito.verify(subjectRepository, Mockito.times(1)).save(subjectToSave);
        Mockito.verify(modelMapper, Mockito.times(1)).map(savedSubject, SubjectDTO.class);

        assertNotNull(result);
        assertEquals(savedSubjectDTO, result);
    }



    @Test
    void testGetSubjectById() {
        Long subjectId = 1L;
        Subject expectedSubject = new Subject();
        SubjectDTO expectedSubjectDTO = new SubjectDTO();

        Mockito.when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(expectedSubject));
        Mockito.when(modelMapper.map(expectedSubject, SubjectDTO.class)).thenReturn(expectedSubjectDTO);

        SubjectDTO result = subjectService.getSubjectById(subjectId);

        Mockito.verify(subjectRepository, Mockito.times(1)).findById(subjectId);
        Mockito.verify(modelMapper, Mockito.times(1)).map(expectedSubject, SubjectDTO.class);

        assertNotNull(result);
        assertEquals(expectedSubjectDTO, result);
    }

    @Test
    void testGetSubjectByIdNotFound() {
        Long subjectId = 1L;

        Mockito.when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> subjectService.getSubjectById(subjectId));
    }

    @Test
    void testUpdateSubject() {
        Long subjectId = 1L;
        SubjectDTO updatedSubjectDTO = new SubjectDTO();
        Subject existingSubject = new Subject();
        Subject updatedSubject = new Subject();
        SubjectDTO expectedUpdatedSubjectDTO = new SubjectDTO();

        Mockito.when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(existingSubject));
        Mockito.when(subjectRepository.save(existingSubject)).thenReturn(updatedSubject);
        Mockito.when(modelMapper.map(updatedSubject, SubjectDTO.class)).thenReturn(expectedUpdatedSubjectDTO);

        SubjectDTO result = subjectService.update(subjectId, updatedSubjectDTO);

        Mockito.verify(subjectRepository, Mockito.times(1)).findById(subjectId);
        Mockito.verify(subjectRepository, Mockito.times(1)).save(existingSubject);
        Mockito.verify(modelMapper, Mockito.times(1)).map(updatedSubject, SubjectDTO.class);

        assertNotNull(result);
        assertEquals(expectedUpdatedSubjectDTO, result);
    }

    @Test
    void testUpdateSubjectNotFound() {
        Long subjectId = 1L;
        SubjectDTO updatedSubjectDTO = new SubjectDTO();

        Mockito.when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> subjectService.update(subjectId, updatedSubjectDTO));
    }

    @Test
    void testDeleteSubject() {
        Long subjectId = 1L;

        subjectService.delete(subjectId);

        Mockito.verify(subjectRepository, Mockito.times(1)).deleteById(subjectId);
    }
}