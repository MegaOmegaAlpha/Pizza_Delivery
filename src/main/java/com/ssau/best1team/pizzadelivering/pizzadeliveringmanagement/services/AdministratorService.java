package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.AdministratorDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Administrator;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.AdministratorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    private AdministratorRepository administratorRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository,
                                ModelMapper modelMapper) {
        this.administratorRepository = administratorRepository;
        this.modelMapper = modelMapper;
    }

    public AdministratorDTO findById(long adminId) throws EntityNotFoundException {
        return convertToDTO((Administrator) administratorRepository.findById(adminId).orElseThrow(EntityNotFoundException::new));
    }

    private AdministratorDTO convertToDTO(Administrator administrator) {
        return modelMapper.map(administrator, AdministratorDTO.class);
    }

}
