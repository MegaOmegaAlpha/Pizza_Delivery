package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.AuthenticationRequest;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.JwtAuthenticationResponse;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private UserAuthenticationService userAuthenticationService;

    @Autowired
    public AuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PutMapping(value = "/auth")
    public JwtAuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws EntityNotFoundException {
        return userAuthenticationService.authenticate(request);
    }

}
