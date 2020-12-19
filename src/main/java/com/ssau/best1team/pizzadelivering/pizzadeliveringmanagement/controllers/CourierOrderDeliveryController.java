package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.OrderDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.CourierOrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api")
public class CourierOrderDeliveryController {

    private CourierOrderResource courierOrderResource;

    @Autowired
    public CourierOrderDeliveryController(CourierOrderResource courierOrderResource) {
        this.courierOrderResource = courierOrderResource;
    }

    @GetMapping(value = "/courier/{courierId}/orders")
    public List<OrderDTO> getOrdersForCourier(@PathVariable long courierId) {
        return courierOrderResource.getOrdersByCourierId(courierId);
    }


}
