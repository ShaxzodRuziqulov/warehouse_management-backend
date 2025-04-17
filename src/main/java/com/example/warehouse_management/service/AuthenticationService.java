package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.User;
import com.example.warehouse_management.repository.UserRepository;
import com.example.warehouse_management.service.dto.LoginUserDto;
import com.example.warehouse_management.service.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthenticationService(UserRepository userRepository,AuthenticationManager authenticationManager, UserService userService) {
        this.userRepository = userRepository;

        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Transactional
    public UserDto signUp(UserDto userDto) {
        return userService.create(userDto);
    }

    public User login(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUsername(),
                        loginUserDto.getPassword()
                )
        );

        return userRepository.findByUsername(loginUserDto.getUsername())
                .orElseThrow();
    }


}
