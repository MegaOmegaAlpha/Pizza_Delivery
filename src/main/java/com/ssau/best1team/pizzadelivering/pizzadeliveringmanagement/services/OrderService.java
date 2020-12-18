package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderStatusDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.OrderNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.OrderStatusNotFound;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Order;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.OrderStatus;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.OrderRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.OrderStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;
    private ModelMapper modelMapper;

    /**
     * todo: эта херня должна содержать айди статуса "доставлено", чтобы фильтровать запросы
     */
    private static final long DELIVERED_ORDER_STATUS = 1;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO modifyOrderStatus(long orderId, long statusId) throws EntityNotFoundException {
        OrderStatus orderStatus = orderStatusRepository.findById(statusId).orElseThrow(OrderStatusNotFound::new);
        Order orderToUpdate = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderToUpdate.setOrderStatus(orderStatus);
        orderToUpdate.setLastStatusUpdateTime(Time.valueOf(LocalTime.now()));
        return convertToDTO(orderRepository.save(orderToUpdate));
    }

    public List<OrderDTO> findAllActive() {
        return orderRepository.findOrdersIgnoringSpecificOrderStatus(DELIVERED_ORDER_STATUS)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order entity) {
        return modelMapper.map(entity, OrderDTO.class);
    }
}
