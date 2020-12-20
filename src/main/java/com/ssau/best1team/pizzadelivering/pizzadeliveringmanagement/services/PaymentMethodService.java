package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.PaymentMethodDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.PaymentMethod;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.PaymentMethodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository,
                                ModelMapper modelMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelMapper = modelMapper;
    }

    public List<PaymentMethodDTO> findAll() {
        return paymentMethodRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PaymentMethodDTO convertToDTO(PaymentMethod entity) {
        return modelMapper.map(entity, PaymentMethodDTO.class);
    }

}
