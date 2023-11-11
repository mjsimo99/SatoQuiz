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

    @GetMapping("/all")
    public List<Level> getAllLevels() {
        return levelService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Level> getLevelById(@PathVariable Long id) {
        Level level = levelService.getById(id);
        return ResponseEntity.ok(level);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Level> updateLevel(@PathVariable Long id, @RequestBody Level level) {
        Level updatedLevel = levelService.update(id, level);
        return ResponseEntity.ok(updatedLevel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        levelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
