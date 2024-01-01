package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.dto.AnswerDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.AnswerRespDTO;
import com.example.satoruquizzes.satoquiz.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/add")
    public ResponseEntity<AnswerDTO> addAnswer(@RequestBody AnswerDTO answerDTO) {
        AnswerDTO savedAnswerDTO = answerService.save(answerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnswerDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnswerRespDTO>> getAllAnswers() {
        List<AnswerRespDTO> answers = answerService.getAllAnswers();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<AnswerRespDTO> getAnswerById(@PathVariable Long answerId) {
        AnswerRespDTO answerRespDTO = answerService.getAnswerById(answerId);
        return ResponseEntity.ok(answerRespDTO);
    }

    @PutMapping("/update/{answerId}")
    public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable Long answerId, @RequestBody AnswerDTO updatedAnswerDTO) {
        AnswerDTO updatedEntityDTO = answerService.update(answerId, updatedAnswerDTO);
        return ResponseEntity.ok(updatedEntityDTO);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswer(answerId);
        return ResponseEntity.noContent().build();
    }
}