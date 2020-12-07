package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.PizzaDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CourierRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CustomerRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PizzaController {

    private PizzaService pizzaService;

    @Autowired
    CourierRepository courierRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping(value = "/pizzas")
    public List<PizzaDTO> getAll() {
        return pizzaService.findAll();
    }

    @GetMapping(value = "/pizzas/{id}")
    public PizzaDTO getById(@PathVariable long id) {
        return pizzaService.findById(id);
    }

    @PostMapping(value = "/pizzas")
    public PizzaDTO save(@RequestBody PizzaDTO pizzaDTO) {
        return pizzaService.save(pizzaDTO);
    }

    @PutMapping(value = "/pizzas")
    public PizzaDTO update(@RequestBody PizzaDTO pizzaDTO) {
        return pizzaService.save(pizzaDTO);
    }

}
