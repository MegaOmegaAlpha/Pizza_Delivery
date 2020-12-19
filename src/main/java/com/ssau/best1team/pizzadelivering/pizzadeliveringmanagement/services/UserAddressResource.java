package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.AddressDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Address;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.User;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.AddressRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAddressResource {

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserAddressResource(CustomerRepository customerRepository,
                               AddressRepository addressRepository,
                               ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    public List<AddressDTO> getAddressesByUserId(long customerId) {
        return addressRepository.findAllByUserId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO saveAddressForUser(long userId, AddressDTO addressDTO) throws EntityNotFoundException {
        User user = customerRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        Address address = new Address();
        address.setFlatNumber(addressDTO.getFlatNumber());
        address.setHouse(addressDTO.getHouse());
        address.setStreet(addressDTO.getStreet());

        user.getAddresses().add(address);
        user = customerRepository.save(user);

        Address toReturn = user.getAddresses()
                .stream()
                .sorted((o1, o2) -> (int) (o2.getId() - o1.getId()))
                .collect(Collectors.toList())
                .get(0);

        return convertToDTO(toReturn);
    }

    public void remove(long userId, long addressId) throws EntityNotFoundException {
        User user = customerRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Address address = addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);

        user.getAddresses().remove(address);
        addressRepository.delete(address);

        customerRepository.save(user);
    }

    private AddressDTO convertToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

}
