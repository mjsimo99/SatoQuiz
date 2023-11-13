package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.model.entity.Answer;
import com.example.satoruquizzes.satoquiz.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/add")
    public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer) {
        Answer savedAnswer = answerService.save(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnswer);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long answerId) {
        Answer answer = answerService.getAnswerById(answerId);
        return ResponseEntity.ok(answer);
    }

    @PutMapping("/update/{answerId}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long answerId, @RequestBody Answer updatedAnswer) {
        Answer updatedEntity = answerService.update(answerId, updatedAnswer);
        return ResponseEntity.ok(updatedEntity);
    }
    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswer(answerId);
        return ResponseEntity.noContent().build();
    }
}