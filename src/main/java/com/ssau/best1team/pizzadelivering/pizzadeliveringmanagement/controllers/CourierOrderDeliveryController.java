package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.CourierOrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class CourierOrderDeliveryController {

    private CourierOrderResource courierOrderResource;

    @Autowired
    public CourierOrderDeliveryController(CourierOrderResource courierOrderResource) {
        this.courierOrderResource = courierOrderResource;
    }


}
