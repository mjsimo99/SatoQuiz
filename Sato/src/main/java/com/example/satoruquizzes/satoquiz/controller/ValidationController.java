package com.example.satoruquizzes.satoquiz.controller;


import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.ValidationDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Validation;
import com.example.satoruquizzes.satoquiz.service.ValidationService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/validations")
@CrossOrigin
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping("/add")
    public ResponseEntity<ValidationDTO> addValidation(@RequestBody ValidationDTO validationDTO) {
        ValidationDTO savedValidationDTO = validationService.save(validationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedValidationDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ValidationDTO>> getAllValidations() {
        List<ValidationDTO> validations = validationService.getAllValidations();
        return ResponseEntity.ok(validations);
    }

    @GetMapping("/{questionId}/{answerId}")
    public ResponseEntity<ValidationDTO> getValidationByIds(@PathVariable Long questionId, @PathVariable Long answerId) {
        ValidationDTO validationDTO = validationService.getValidationByIds(questionId, answerId);
        return ResponseEntity.ok(validationDTO);
    }
    @DeleteMapping("/{questionId}/{answerId}")
    public ResponseEntity<String> deleteValidation(@PathVariable Long questionId, @PathVariable Long answerId) {
        try {
            validationService.delete(questionId, answerId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Validation not found for questionId=" + questionId + ", answerId=" + answerId);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting validation.");
        }
    }
    @PutMapping("/update/{questionId}/{answerId}")
    public ResponseEntity<ValidationDTO> updateValidation(@PathVariable Long questionId, @PathVariable Long answerId, @RequestBody ValidationDTO validationDTO) {
        try {
            ValidationDTO updatedValidationDTO = validationService.update(questionId, answerId, validationDTO);
            return ResponseEntity.ok(updatedValidationDTO);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
