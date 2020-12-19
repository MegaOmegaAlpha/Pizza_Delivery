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
public class AdminOrderController {

    private UserOrderResource userOrderResource;

    @Autowired
    public AdminOrderController(UserOrderResource userOrderResource) {
        this.userOrderResource = userOrderResource;
    }

    @GetMapping(value = "/admin/{adminId}/orders")
    public List<OrderDTO> getOrdersForCustomer(@PathVariable long adminId) {
        return userOrderResource.getUserOrders(adminId);
    }

    @GetMapping(value = "/admin/{adminId}/orders/{orderId}")
    public OrderDTO getOrderForCustomer(@PathVariable long adminId, @PathVariable long orderId) {
        return userOrderResource.getUserOrder(adminId, orderId);
    }

    @PostMapping(value = "/admin/{adminId}/orders")
    public OrderDTO createOrderForCustomer(@PathVariable long adminId, @RequestBody OrderDTO orderDTO) throws EntityNotFoundException {
        return userOrderResource.createOrder(adminId, orderDTO);
    }

}
