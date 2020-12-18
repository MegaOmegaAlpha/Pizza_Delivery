package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.AddressDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Address;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Customer;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.AddressRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerAddressResource {

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerAddressResource(CustomerRepository customerRepository,
                                   AddressRepository addressRepository,
                                   ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    public List<AddressDTO> getAddressesByUserId(long customerId) {
        return addressRepository.findAllByCustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO saveAddressForUser(long customerId, AddressDTO addressDTO) throws EntityNotFoundException {
        Customer customer = (Customer) customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);

        Address address = new Address();
        address.setFlatNumber(addressDTO.getFlatNumber());
        address.setHouse(addressDTO.getHouse());
        address.setStreet(addressDTO.getStreet());

        customer.getAddresses().add(address);
        customer = customerRepository.save(customer);

        Address toReturn = customer.getAddresses()
                .stream()
                .sorted((o1, o2) -> (int) (o2.getId() - o1.getId()))
                .collect(Collectors.toList())
                .get(0);

        return convertToDTO(toReturn);
    }

    public void remove(long customerId, long addressId) throws EntityNotFoundException {
        Customer customer = (Customer) customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);
        Address address = addressRepository.findById(addressId).orElseThrow(EntityNotFoundException::new);

        customer.getAddresses().remove(address);
        addressRepository.delete(address);

        customerRepository.save(customer);
    }

    private AddressDTO convertToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

}
