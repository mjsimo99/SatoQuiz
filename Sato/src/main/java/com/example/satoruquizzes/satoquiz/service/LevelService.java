package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.CustomRuntimeException;
import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.LevelDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.LevelRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Level;
import com.example.satoruquizzes.satoquiz.repository.LevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelService {

    private final LevelRepository levelRepository;

    private final ModelMapper modelMapper;

    public LevelService(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }

    public LevelDTO save(LevelDTO levelDTO) {
        try {
            Level level = modelMapper.map(levelDTO, Level.class);
            level = levelRepository.save(level);
            return modelMapper.map(level, LevelDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while saving level: " + e.getMessage());
        }
    }

    public List<LevelRespDTO> getAll() {
        try {
            List<Level> levels = levelRepository.findAll();
            return levels.stream()
                    .map(level -> modelMapper.map(level, LevelRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching all levels: " + e.getMessage());
        }
    }

    public LevelRespDTO getById(Long id) {
        try {
            Level level = levelRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Level not found for id: " + id));
            return modelMapper.map(level, LevelRespDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while fetching level by id: " + id + ": " + e.getMessage());
        }
    }

    public LevelDTO update(Long id, LevelDTO newLevelDTO) {
        try {
            Level existingLevel = levelRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Level not found for id: " + id));

            existingLevel.setDescription(newLevelDTO.getDescription());
            existingLevel.setMaxPoints(newLevelDTO.getMaxPoints());
            existingLevel.setMinPoints(newLevelDTO.getMinPoints());

            return modelMapper.map(levelRepository.save(existingLevel), LevelDTO.class);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while updating level: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            levelRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomRuntimeException("Error while deleting level: " + e.getMessage());
        }
    }
}
