package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.AddressDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.UserAddressResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerAddressController {

    private UserAddressResource userAddressResource;

    @Autowired
    public CustomerAddressController(UserAddressResource userAddressResource) {
        this.userAddressResource = userAddressResource;
    }

    @GetMapping(value = "/customer/{customerId}/addresses")
    public List<AddressDTO> getAddressesForCustomer(@PathVariable long customerId) {
        return userAddressResource.getAddressesByUserId(customerId);
    }

    @PostMapping(value = "/customer/{customerId}/addresses")
    public AddressDTO createAddressForUser(@PathVariable long customerId, @RequestBody AddressDTO addressDTO) throws EntityNotFoundException {
        return userAddressResource.saveAddressForUser(customerId, addressDTO);
    }

    @DeleteMapping(value = "/customer/{customerId}/addresses/{addressId}")
    public ResponseEntity<Object> removeAddressForUCustomer(@PathVariable long customerId, @PathVariable long addressId) {
        try {
            userAddressResource.remove(customerId, addressId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
