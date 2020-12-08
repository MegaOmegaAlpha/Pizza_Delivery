package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.CustomerDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Customer;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerDTO findById(long customerId) throws EntityNotFoundException {
        return convertToDTO((Customer) customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new));
    }

    public CustomerDTO update(CustomerDTO customerDTO) throws EntityNotFoundException {
        Customer customer = (Customer) customerRepository.findById(customerDTO.getId()).orElseThrow(EntityNotFoundException::new);
        customer.setTelephone(customerDTO.getTelephone());
        customer.setEmail(customerDTO.getEmail());
        customer.setFullName(customerDTO.getFullName());
        customer.setBirthDate(new Date(customerDTO.getBirthDate().getTime()));
        customer.setLogin(customerDTO.getLogin());

        return convertToDTO(customerRepository.save(customer));
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setLogin(customerDTO.getLogin());
        customer.setFullName(customerDTO.getFullName());
        customer.setBirthDate(new Date(customerDTO.getBirthDate().getTime()));
        customer.setEmail(customerDTO.getEmail());
        customer.setTelephone(customerDTO.getTelephone());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));

        return convertToDTO(customerRepository.save(customer));
    }

    private CustomerDTO convertToDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

}
