package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.EntityNotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.SalonDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.SalonRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Salon;
import com.example.satoruquizzes.satoquiz.repository.SalonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalonService {

    private final SalonRepository salonRepository;
    private final ModelMapper modelMapper;

    public SalonService(SalonRepository salonRepository, ModelMapper modelMapper) {
        this.salonRepository = salonRepository;
        this.modelMapper = modelMapper;
    }

    public SalonRespDTO getSalonById(Long salonId) {
        try {
            Salon salon = salonRepository.findById(salonId)
                    .orElseThrow(() -> new EntityNotFoundException("Salon not found with id: " + salonId));

            return modelMapper.map(salon, SalonRespDTO.class);
        } catch (Exception e) {
            throw new EntityNotFoundException("Error while fetching salon with id: " + salonId + ": " + e.getMessage());
        }
    }

    public List<SalonRespDTO> getAllSalons() {
        try {
            List<Salon> salons = salonRepository.findAll();
            return salons.stream()
                    .map(salon -> modelMapper.map(salon, SalonRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new EntityNotFoundException("Error while fetching all salons: " + e.getMessage());
        }
    }

    public SalonDTO createSalon(SalonDTO salonDTO) {
        try {
            Salon salon = modelMapper.map(salonDTO, Salon.class);
            salon.setParticipates(null);

            Salon savedSalon = salonRepository.save(salon);

            return modelMapper.map(savedSalon, SalonDTO.class);
        } catch (Exception e) {
            throw new EntityNotFoundException("Error while creating salon: " + e.getMessage());
        }
    }

    public void deleteSalon(Long salonId) {
        try {
            Salon salon = salonRepository.findById(salonId)
                    .orElseThrow(() -> new EntityNotFoundException("Salon not found with id: " + salonId));

            salonRepository.delete(salon);
        } catch (Exception e) {
            throw new EntityNotFoundException("Error while deleting salon with id: " + salonId + ": " + e.getMessage());
        }
    }
}
