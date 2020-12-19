package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.UserOrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerOrderController {

    private UserOrderResource userOrderResource;

    @Autowired
    public CustomerOrderController(UserOrderResource userOrderResource) {
        this.userOrderResource = userOrderResource;
    }

    @GetMapping(value = "/customer/{customerId}/orders")
    public List<OrderDTO> getOrdersForCustomer(@PathVariable long customerId) {
        return userOrderResource.getUserOrders(customerId);
    }

    @GetMapping(value = "/customer/{customerId}/orders/{orderId}")
    public OrderDTO getOrderForCustomer(@PathVariable long customerId, @PathVariable long orderId) {
        return userOrderResource.getUserOrder(customerId, orderId);
    }

    @PostMapping(value = "/customer/{customerId}/orders")
    public OrderDTO createOrderForCustomer(@PathVariable long customerId, @RequestBody OrderDTO orderDTO) throws EntityNotFoundException {
        return userOrderResource.createOrder(customerId, orderDTO);
    }

}
