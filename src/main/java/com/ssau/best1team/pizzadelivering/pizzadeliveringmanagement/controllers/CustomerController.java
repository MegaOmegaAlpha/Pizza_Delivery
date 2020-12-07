package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.CustomerDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customer/{customerId}")
    public CustomerDTO findCustomerById(@PathVariable long customerId) throws EntityNotFoundException {
        return customerService.findById(customerId);
    }

    @PutMapping(value = "/customer")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) throws EntityNotFoundException {
        return customerService.update(customerDTO);
    }

    @PostMapping(value = "/register")
    public CustomerDTO register(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

}
