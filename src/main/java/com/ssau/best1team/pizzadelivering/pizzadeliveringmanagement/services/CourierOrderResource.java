package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDeliveryDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.OrderDelivery;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CourierRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.OrderDeliveryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourierOrderResource {

    private OrderDeliveryRepository orderDeliveryRepository;
    private CourierRepository courierRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CourierOrderResource(OrderDeliveryRepository orderDeliveryRepository, CourierRepository courierRepository, ModelMapper modelMapper) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.courierRepository = courierRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderDeliveryDTO> getOrdersByCourierId(long courierId) {
        return orderDeliveryRepository.getAllByCourierId(courierId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderDeliveryDTO convertToDTO(OrderDelivery delivery) {
        return modelMapper.map(delivery, OrderDeliveryDTO.class);
    }

}
