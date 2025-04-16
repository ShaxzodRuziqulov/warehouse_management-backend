package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.User;
import com.example.warehouse_management.entity.enumirated.Status;
import com.example.warehouse_management.repository.UserRepository;
import com.example.warehouse_management.service.dto.UserDto;
import com.example.warehouse_management.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
    public UserDto update(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<UserDto> allUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors
                        .toList());
    }

    public User findUserId(Long id) {
        return userRepository
                .findById(id)
                .orElseGet(User::new);
    }

    public User deleteuser(Long id) {
        return userRepository.updateStatus(id, Status.DELETED);
    }
}
