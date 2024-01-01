package com.example.satoruquizzes.satoquiz.service;

import com.example.satoruquizzes.satoquiz.exception.EntityNotFoundException;
import com.example.satoruquizzes.satoquiz.model.dto.SalonDTO;
import com.example.satoruquizzes.satoquiz.model.dto.responseDto.SalonRespDTO;
import com.example.satoruquizzes.satoquiz.model.entity.Salon;
import com.example.satoruquizzes.satoquiz.repository.SalonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalonService {

    private final SalonRepository salonRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SalonService(SalonRepository salonRepository, ModelMapper modelMapper) {
        this.salonRepository = salonRepository;
        this.modelMapper = modelMapper;
    }

    public SalonRespDTO getSalonById(Long salonId) {
        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new EntityNotFoundException("Salon not found with id: " + salonId));

        return modelMapper.map(salon, SalonRespDTO.class);
    }

    public List<SalonRespDTO> getAllSalons() {
        List<Salon> salons = salonRepository.findAll();
        return salons.stream()
                .map(salon -> modelMapper.map(salon, SalonRespDTO.class))
                .collect(Collectors.toList());
    }

    public SalonDTO createSalon(SalonDTO salonDTO) {
        Salon salon = modelMapper.map(salonDTO, Salon.class);
        salon.setParticipates(null);

        Salon savedSalon = salonRepository.save(salon);

        return modelMapper.map(savedSalon, SalonDTO.class);
    }

    public void deleteSalon(Long salonId) {
        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new EntityNotFoundException("Salon not found with id: " + salonId));

        salonRepository.delete(salon);
    }
}
