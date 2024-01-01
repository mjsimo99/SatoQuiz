package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.NotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.SubjectDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.SubjectRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Subject;
import com.example.satoruquizzes.satoquiz.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final ModelMapper modelMapper;

    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    public SubjectDTO save(SubjectDTO subjectDTO) {
        try {
            Subject subject = modelMapper.map(subjectDTO, Subject.class);
            return modelMapper.map(subjectRepository.save(subject), SubjectDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while saving subject: " + e.getMessage());
        }
    }

    public List<SubjectRespDTO> getAll() {
        try {
            List<Subject> subjects = subjectRepository.findAll();
            return subjects.stream()
                    .map(subject -> modelMapper.map(subject, SubjectRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching all subjects: " + e.getMessage());
        }
    }

    public SubjectRespDTO getSubjectById(Long subjectId) {
        try {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new NotFoundException("Subject not found for ID: " + subjectId));
            return modelMapper.map(subject, SubjectRespDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while fetching subject with ID: " + subjectId + ": " + e.getMessage());
        }
    }

    public void delete(Long subjectId) {
        try {
            subjectRepository.deleteById(subjectId);
        } catch (Exception e) {
            throw new NotFoundException("Error while deleting subject with ID: " + subjectId + ": " + e.getMessage());
        }
    }

    public SubjectDTO update(Long subjectId, SubjectDTO newSubjectDTO) {
        try {
            Subject existingSubject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new NotFoundException("Subject not found for ID: " + subjectId));

            existingSubject.setIntitule(newSubjectDTO.getIntitule());

            if (newSubjectDTO.getParent() != null) {
                Subject newParent = subjectRepository.findById(newSubjectDTO.getParent())
                        .orElseThrow(() -> new NotFoundException("Parent subject not found for ID: " + newSubjectDTO.getParent()));
                existingSubject.setParent(newParent);
            }

            Subject updatedSubject = subjectRepository.save(existingSubject);
            return modelMapper.map(updatedSubject, SubjectDTO.class);
        } catch (Exception e) {
            throw new NotFoundException("Error while updating subject with ID: " + subjectId + ": " + e.getMessage());
        }
    }
}
