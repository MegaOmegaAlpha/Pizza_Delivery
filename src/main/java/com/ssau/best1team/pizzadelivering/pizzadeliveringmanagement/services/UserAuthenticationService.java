package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.AuthenticationRequest;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.JwtAuthenticationResponse;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.UserDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Role;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.User;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.UserRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.security.jwt.JwtTokenProvider;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.security.jwt.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserAuthenticationService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private ModelMapper modelMapper;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository,
                                     AuthenticationManager authenticationManager,
                                     JwtTokenProvider jwtTokenProvider,
                                     ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }

    public JwtAuthenticationResponse authenticate(AuthenticationRequest request) throws EntityNotFoundException {
        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(EntityNotFoundException::new);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        String token = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(
                UserPrincipal.create(user),
                user.getRoleList())
        );

        UserDTO userDTO = convertToDTO(user);
        userDTO.setRoleList(
                user.getRoleList()
                        .stream()
                        .map(this::acquireName)
                        .collect(Collectors.toList())
        );

        return new JwtAuthenticationResponse(token, userDTO);
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private String acquireName(Role role) {
        return role.getName();
    }

}
