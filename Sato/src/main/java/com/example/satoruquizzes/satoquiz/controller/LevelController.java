package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.LevelDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.LevelRespDTO;
import com.example.satoruquizzes.satoquiz.service.LevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/levels")
@CrossOrigin
public class LevelController {

    private final LevelService levelService;

    // Constructor injecting LevelService dependency
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    // Add a new level
    @PostMapping("/add")
    public ResponseEntity<LevelDTO> addLevel(@RequestBody LevelDTO levelDTO) {
        // Save the level using the service
        LevelDTO savedLevelDTO = levelService.save(levelDTO);
        // Return the saved level with HTTP status 200 (OK)
        return ResponseEntity.ok(savedLevelDTO);
    }

    // Get all levels
    @GetMapping("/all")
    public List<LevelRespDTO> getAllLevels() {
        // Retrieve all levels using the service
        return levelService.getAll();
    }

    // Get a specific level by ID
    @GetMapping("/{id}")
    public ResponseEntity<LevelRespDTO> getLevelById(@PathVariable Long id) {
        // Retrieve the level by ID using the service
        LevelRespDTO levelRespDTO = levelService.getById(id);
        // Return the level with HTTP status 200 (OK)
        return ResponseEntity.ok(levelRespDTO);
    }

    // Update an existing level
    @PutMapping("/update/{id}")
    public ResponseEntity<LevelDTO> updateLevel(@PathVariable Long id, @RequestBody LevelDTO levelDTO) {
        // Update the level using the service
        LevelDTO updatedLevelDTO = levelService.update(id, levelDTO);
        // Return the updated level with HTTP status 200 (OK)
        return ResponseEntity.ok(updatedLevelDTO);
    }

    // Delete a specific level by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        // Delete the level using the service
        levelService.delete(id);
        // Return a no-content response with HTTP status 204 (NO CONTENT)
        return ResponseEntity.noContent().build();
    }
}
