package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.AdministratorDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AdministratorController {

    private AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping(value = "/admin/{adminId}")
    public AdministratorDTO findById(@PathVariable long adminId) throws EntityNotFoundException {
        return administratorService.findById(adminId);
    }

}
