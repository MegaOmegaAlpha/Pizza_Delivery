package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Courier;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Order;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.OrderDelivery;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CourierRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.OrderDeliveryRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourierOrderResource {

    private OrderDeliveryRepository orderDeliveryRepository;
    private OrderRepository orderRepository;
    private CourierRepository courierRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CourierOrderResource(OrderDeliveryRepository orderDeliveryRepository,
                                OrderRepository orderRepository,
                                CourierRepository courierRepository,
                                ModelMapper modelMapper) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getOrdersByCourierId(long courierId) {
        return orderRepository.findOrderForCourier(courierId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void assignOrderToCourier(long orderId) throws EntityNotFoundException {
        Order readyOrder = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        Courier courier = courierRepository.findMostFree().get(0);
        OrderDelivery orderDelivery = new OrderDelivery();
        orderDelivery.setOrder(readyOrder);
        orderDelivery.setCourier(courier);
        orderDelivery.setTimeStart(Time.valueOf(LocalTime.now()));

        orderDeliveryRepository.save(orderDelivery);
    }

    public void finishOrderDelivering(long orderId) throws EntityNotFoundException {
        Order finishedOrder = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        OrderDelivery orderDelivery = orderDeliveryRepository.findById(finishedOrder.getOrderDelivery().getId()).orElseThrow(EntityNotFoundException::new);
        orderDelivery.setTimeFinish(Time.valueOf(LocalTime.now()));

        orderDeliveryRepository.save(orderDelivery);
    }

    private OrderDTO convertToDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }
}
