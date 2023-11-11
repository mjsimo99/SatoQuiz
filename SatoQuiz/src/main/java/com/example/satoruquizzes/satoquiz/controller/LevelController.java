package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.entity.Level;
import com.example.satoruquizzes.satoquiz.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/levels")
public class LevelController {
    @Autowired
    private LevelService levelService;

    @PostMapping("/add")
    public ResponseEntity<Level> addLevel(@RequestBody Level level) {
        Level savedLevel = levelService.save(level);
        return ResponseEntity.ok(savedLevel);
    }

    @PutMapping("/{levelId}")
    public ResponseEntity<Level> updateLevel(@PathVariable Long levelId, @RequestBody Level updatedLevel) {
        Level updated = levelService.updateLevel(levelId, updatedLevel);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Level>> getAllLevels() {
        List<Level> levels = levelService.getAllLevels();
        return ResponseEntity.ok(levels);
    }

    @GetMapping("/{levelId}")
    public ResponseEntity<Level> getLevelById(@PathVariable Long levelId) {
        Level level = levelService.getLevelById(levelId);
        return ResponseEntity.ok(level);
    }

    @DeleteMapping("/{levelId}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long levelId) {
        levelService.deleteLevel(levelId);
        return ResponseEntity.noContent().build();
    }
}

