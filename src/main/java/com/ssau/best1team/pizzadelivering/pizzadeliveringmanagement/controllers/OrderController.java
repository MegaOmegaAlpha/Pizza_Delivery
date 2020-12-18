package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderStatusDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "admin/orders")
    public List<OrderDTO> getAll() {
        return orderService.findAll();
    }

    @PutMapping(value = "admin//orders/status")
    public OrderDTO updateOrderStatus(@RequestBody OrderDTO orderDTO) throws EntityNotFoundException {
        return orderService.modifyOrderStatus(orderDTO);
    }

    @GetMapping(value = "/admin/orders/active")
    public List<OrderDTO> getActiveOrders() {
        return orderService.findAllActive();
    }

}
