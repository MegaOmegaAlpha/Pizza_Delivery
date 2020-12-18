package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderStatusDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.OrderStatus;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.OrderStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderStatusService {

    private OrderStatusRepository orderStatusRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrderStatusService(OrderStatusRepository orderStatusRepository, ModelMapper modelMapper) {
        this.orderStatusRepository = orderStatusRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderStatusDTO> findAll() {
        return orderStatusRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderStatusDTO convertToDTO(OrderStatus orderStatus) {
        return modelMapper.map(orderStatus, OrderStatusDTO.class);
    }

}
