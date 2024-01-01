package com.example.satoruquizzes.satoquiz.controller;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.ValidationDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.ValidationRespDTO;
import com.example.satoruquizzes.satoquiz.service.ValidationService;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/validations")
@CrossOrigin
public class ValidationController {

    private final ValidationService validationService;

    // Constructor injecting ValidationService dependency
    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    // Add a new validation
    @PostMapping("/add")
    public ResponseEntity<ValidationDTO> addValidation(@RequestBody ValidationDTO validationDTO) {
        // Save the validation using the service
        ValidationDTO savedValidationDTO = validationService.save(validationDTO);
        // Return the saved validation with HTTP status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedValidationDTO);
    }

    // Get all validations
    @GetMapping("/all")
    public ResponseEntity<List<ValidationRespDTO>> getAllValidations() {
        // Retrieve all validations using the service
        List<ValidationRespDTO> validations = validationService.getAllValidations();
        // Return the list of validations with HTTP status 200 (OK)
        return ResponseEntity.ok(validations);
    }

    // Get a specific validation by question ID and answer ID
    @GetMapping("/{questionId}/{answerId}")
    public ResponseEntity<ValidationRespDTO> getValidationByIds(@PathVariable Long questionId, @PathVariable Long answerId) {
        // Retrieve the validation by question ID and answer ID using the service
        ValidationRespDTO validationRespDTO = validationService.getValidationByIds(questionId, answerId);
        // Return the validation with HTTP status 200 (OK)
        return ResponseEntity.ok(validationRespDTO);
    }

    // Delete a specific validation by question ID and answer ID
    @DeleteMapping("/{questionId}/{answerId}")
    public ResponseEntity<String> deleteValidation(@PathVariable Long questionId, @PathVariable Long answerId) {
        try {
            // Delete the validation by question ID and answer ID using the service
            validationService.delete(questionId, answerId);
            // Return a no-content response with HTTP status 204 (NO CONTENT)
            return ResponseEntity.noContent().build();
        } catch (NotFoundException ex) {
            // Return a not-found response with HTTP status 404 (NOT FOUND)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Validation not found for questionId=" + questionId + ", answerId=" + answerId);
        } catch (Exception ex) {
            // Return an internal server error response with HTTP status 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting validation.");
        }
    }

    // Update a specific validation by question ID and answer ID
    @PutMapping("/update/{questionId}/{answerId}")
    public ResponseEntity<ValidationDTO> updateValidation(@PathVariable Long questionId, @PathVariable Long answerId, @RequestBody ValidationDTO validationDTO) {
        try {
            // Update the validation by question ID and answer ID using the service
            ValidationDTO updatedValidationDTO = validationService.update(questionId, answerId, validationDTO);
            // Return the updated validation with HTTP status 200 (OK)
            return ResponseEntity.ok(updatedValidationDTO);
        } catch (NotFoundException ex) {
            // Return a not-found response with HTTP status 404 (NOT FOUND)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ValidationException ex) {
            // Return a bad-request response with HTTP status 400 (BAD REQUEST)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            // Return an internal server error response with HTTP status 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
