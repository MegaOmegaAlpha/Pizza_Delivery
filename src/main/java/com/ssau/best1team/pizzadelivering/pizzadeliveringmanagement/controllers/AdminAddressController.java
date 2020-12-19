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
public class AdminAddressController {

    private UserAddressResource userAddressResource;

    @Autowired
    public AdminAddressController(UserAddressResource userAddressResource) {
        this.userAddressResource = userAddressResource;
    }

    @GetMapping(value = "/admin/{adminId}/addresses")
    public List<AddressDTO> findAllAddressesForAdmin(@PathVariable long adminId) {
        return userAddressResource.getAddressesByUserId(adminId);
    }

    @PostMapping(value = "/admin/{adminId}/addresses")
    public AddressDTO findAllAddressesForAdmin(@PathVariable long adminId, @RequestBody AddressDTO addressDTO) throws EntityNotFoundException {
        return userAddressResource.saveAddressForUser(adminId, addressDTO);
    }

    @DeleteMapping(value = "/admin/{adminId}/addresses/{addressId}")
    public ResponseEntity<Object> removeAddressForUCustomer(@PathVariable long adminId, @PathVariable long addressId) {
        try {
            userAddressResource.remove(adminId, addressId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
