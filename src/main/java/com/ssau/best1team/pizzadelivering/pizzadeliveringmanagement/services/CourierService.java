package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.CourierDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Courier;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CourierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierService {

    private CourierRepository courierRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CourierService(CourierRepository courierRepository, ModelMapper modelMapper) {
        this.courierRepository = courierRepository;
        this.modelMapper = modelMapper;
    }

    public CourierDTO findById(long courierId) throws EntityNotFoundException {
        return convertToDTO((Courier) courierRepository.findById(courierId).orElseThrow(EntityNotFoundException::new));
    }

    private CourierDTO convertToDTO(Courier courier) {
        return modelMapper.map(courier, CourierDTO.class);
    }

}
