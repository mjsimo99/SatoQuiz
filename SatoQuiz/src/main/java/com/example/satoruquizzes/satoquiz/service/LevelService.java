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

    public List<Level> getAll() {
        return levelRepository.findAll();
    }

    public Level getById(Long id) {
        return levelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Level not found for id: " + id));
    }

    public Level update(Long id, Level newLevel) {
        Level existingLevel = getById(id);
        existingLevel.setDescription(newLevel.getDescription());
        existingLevel.setMaxPoints(newLevel.getMaxPoints());
        existingLevel.setMinPoints(newLevel.getMinPoints());

        return levelRepository.save(existingLevel);
    }

    public void delete(Long id) {
        levelRepository.deleteById(id);
    }
}