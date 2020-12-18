package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.security.jwt;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.User;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CourierRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.CustomerRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    /*private CustomerRepository customerRepository;
    private CourierRepository courierRepository;*/

    @Autowired
    public CustomUserDetailsService(CustomerRepository customerRepository, CourierRepository courierRepository, UserRepository userRepository) {
        //this.userRepository = userRepository;
        /*this.customerRepository = customerRepository;
        this.courierRepository = courierRepository;*/
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("User not found with login : " + login)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(long id) throws EntityNotFoundException {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return UserPrincipal.create(user);
    }
}
