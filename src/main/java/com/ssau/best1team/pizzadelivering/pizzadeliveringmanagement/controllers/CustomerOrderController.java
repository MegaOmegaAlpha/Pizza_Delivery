package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.CustomerOrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerOrderController {

    private CustomerOrderResource customerOrderResource;

    @Autowired
    public CustomerOrderController(CustomerOrderResource customerOrderResource) {
        this.customerOrderResource = customerOrderResource;
    }

    @GetMapping(value = "/customer/{customerId}/orders")
    public List<OrderDTO> getOrdersForCustomer(@PathVariable long customerId) {
        return customerOrderResource.getCustomerOrders(customerId);
    }

    @GetMapping(value = "/customer/{customerId}/orders/{orderId}")
    public OrderDTO getOrderForCustomer(@PathVariable long customerId, @PathVariable long orderId) {
        return customerOrderResource.getCustomerOrder(customerId, orderId);
    }

    @PostMapping(value = "/customer/{customerId}/orders")
    public OrderDTO createOrderForCustomer(@PathVariable long customerId, @RequestBody OrderDTO orderDTO) throws EntityNotFoundException {
        return customerOrderResource.createOrder(customerId, orderDTO);
    }

}
