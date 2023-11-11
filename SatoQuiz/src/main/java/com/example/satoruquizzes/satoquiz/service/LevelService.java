package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.entity.Level;
import com.example.satoruquizzes.satoquiz.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    public Level save(Level level) {
        return levelRepository.save(level);
    }

    public Level updateLevel(Long levelId, Level updatedLevel) {
        Level existingLevel = getLevelById(levelId);
        existingLevel.setDescription(updatedLevel.getDescription());
        existingLevel.setMaxPoints(updatedLevel.getMaxPoints());
        existingLevel.setMinPoints(updatedLevel.getMinPoints());
        return save(existingLevel);
    }

    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    public Level getLevelById(Long levelId) {
        return levelRepository.findById(levelId)
                .orElseThrow(() -> new NotFoundException("Level not found for ID: " + levelId));
    }

    public void deleteLevel(Long levelId) {
        levelRepository.deleteById(levelId);
    }
}