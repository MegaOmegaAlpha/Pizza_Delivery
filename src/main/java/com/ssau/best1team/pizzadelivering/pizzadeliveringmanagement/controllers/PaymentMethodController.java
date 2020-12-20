package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.PaymentMethodDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.PaymentMethodService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentMethodController {

    private PaymentMethodService paymentMethodService;

    @GetMapping(value = "/customer/payment-methods")
    public List<PaymentMethodDTO> findAll() {
        return paymentMethodService.findAll();
    }

}
