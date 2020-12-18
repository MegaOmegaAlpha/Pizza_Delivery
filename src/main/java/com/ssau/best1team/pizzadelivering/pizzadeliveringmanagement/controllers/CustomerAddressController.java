package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.controllers;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.AddressDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services.CustomerAddressResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerAddressController {

    private CustomerAddressResource customerAddressResource;

    @Autowired
    public CustomerAddressController(CustomerAddressResource customerAddressResource) {
        this.customerAddressResource = customerAddressResource;
    }

    @GetMapping(value = "/customer/{customerId}/addresses")
    public List<AddressDTO> getAddressesForCustomer(@PathVariable long customerId) {
        return customerAddressResource.getAddressesByUserId(customerId);
    }

    @PostMapping(value = "/customer/{customerId}/addresses")
    public AddressDTO createAddressForUser(@PathVariable long customerId, @RequestBody AddressDTO addressDTO) throws EntityNotFoundException {
        return customerAddressResource.saveAddressForUser(customerId, addressDTO);
    }

    @DeleteMapping(value = "/customer/{customerId}/addresses")
    public ResponseEntity<Object> removeAddressForUCustomer(@PathVariable long customerId, @RequestBody AddressDTO addressDTO) {
        customerAddressResource.remove(addressDTO.getId());
        return ResponseEntity.ok().build();
    }

}
