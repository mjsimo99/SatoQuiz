package com.example.satoruquizzes.satoquiz;


import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.LevelDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Level;
import com.example.satoruquizzes.satoquiz.repository.LevelRepository;
import com.example.satoruquizzes.satoquiz.service.LevelService;
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
class LevelServiceTest {

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LevelService levelService;

    @Test
    void testSaveLevel() {
        LevelDTO levelDTOToSave = new LevelDTO();
        Level levelToSave = new Level();
        Level savedLevel = new Level();
        LevelDTO savedLevelDTO = new LevelDTO();

        Mockito.when(modelMapper.map(levelDTOToSave, Level.class)).thenReturn(levelToSave);
        Mockito.when(levelRepository.save(levelToSave)).thenReturn(savedLevel);
        Mockito.when(modelMapper.map(savedLevel, LevelDTO.class)).thenReturn(savedLevelDTO);

        LevelDTO result = levelService.save(levelDTOToSave);

        Mockito.verify(modelMapper, Mockito.times(1)).map(levelDTOToSave, Level.class);
        Mockito.verify(levelRepository, Mockito.times(1)).save(levelToSave);
        Mockito.verify(modelMapper, Mockito.times(1)).map(savedLevel, LevelDTO.class);

        assertNotNull(result);
        assertEquals(savedLevelDTO, result);
    }

    @Test
    void testGetAllLevels() {
        List<Level> levels = Arrays.asList(new Level(), new Level());
        List<LevelDTO> expectedLevelDTOs = Arrays.asList(new LevelDTO(), new LevelDTO());

        Mockito.when(levelRepository.findAll()).thenReturn(levels);
        Mockito.when(modelMapper.map(Mockito.any(Level.class), Mockito.eq(LevelDTO.class)))
                .thenReturn(expectedLevelDTOs.get(0), expectedLevelDTOs.get(1));

        List<LevelDTO> result = levelService.getAll();

        Mockito.verify(levelRepository, Mockito.times(1)).findAll();
        Mockito.verify(modelMapper, Mockito.times(2)).map(Mockito.any(Level.class), Mockito.eq(LevelDTO.class));

        assertEquals(expectedLevelDTOs, result);
    }

    @Test
    void testGetLevelById() {
        Long levelId = 1L;
        Level expectedLevel = new Level();
        LevelDTO expectedLevelDTO = new LevelDTO();

        Mockito.when(levelRepository.findById(levelId)).thenReturn(Optional.of(expectedLevel));
        Mockito.when(modelMapper.map(expectedLevel, LevelDTO.class)).thenReturn(expectedLevelDTO);

        LevelDTO result = levelService.getById(levelId);

        Mockito.verify(levelRepository, Mockito.times(1)).findById(levelId);
        Mockito.verify(modelMapper, Mockito.times(1)).map(expectedLevel, LevelDTO.class);

        assertNotNull(result);
        assertEquals(expectedLevelDTO, result);
    }

    @Test
    void testGetLevelByIdNotFound() {
        Long levelId = 1L;
        Mockito.when(levelRepository.findById(levelId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> levelService.getById(levelId));
    }

    @Test
    void testUpdateLevel() {
        Long levelId = 1L;
        LevelDTO updatedLevelDTO = new LevelDTO();
        Level existingLevel = new Level();
        Level updatedLevel = new Level();
        LevelDTO expectedUpdatedLevelDTO = new LevelDTO();

        Mockito.when(levelRepository.findById(levelId)).thenReturn(Optional.of(existingLevel));
        Mockito.when(levelRepository.save(existingLevel)).thenReturn(updatedLevel);
        Mockito.when(modelMapper.map(updatedLevel, LevelDTO.class)).thenReturn(expectedUpdatedLevelDTO);

        LevelDTO result = levelService.update(levelId, updatedLevelDTO);

        Mockito.verify(levelRepository, Mockito.times(1)).findById(levelId);
        Mockito.verify(levelRepository, Mockito.times(1)).save(existingLevel);
        Mockito.verify(modelMapper, Mockito.times(1)).map(updatedLevel, LevelDTO.class);

        assertNotNull(result);
        assertEquals(expectedUpdatedLevelDTO, result);
    }

    @Test
    void testUpdateLevelNotFound() {
        Long levelId = 1L;
        LevelDTO updatedLevelDTO = new LevelDTO();
        Mockito.when(levelRepository.findById(levelId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> levelService.update(levelId, updatedLevelDTO));
    }

    @Test
    void testDeleteLevel() {
        Long levelId = 1L;
        levelService.delete(levelId);

        Mockito.verify(levelRepository, Mockito.times(1)).deleteById(levelId);
    }
}