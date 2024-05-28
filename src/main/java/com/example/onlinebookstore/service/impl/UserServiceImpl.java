package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.UserRegistrationRequestDto;
import com.example.onlinebookstore.dto.UserResponseDto;
import com.example.onlinebookstore.exception.RegistrationException;
import com.example.onlinebookstore.mapper.UserMapper;
import com.example.onlinebookstore.model.Role;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.RoleRepository;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.ShoppingCartService;
import com.example.onlinebookstore.service.UserService;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ShoppingCartService shoppingCartService;

    @Override
    public UserResponseDto save(
            UserRegistrationRequestDto requestDto
    ) throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("User with this email is already registered");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Optional<Role> optionalRole = roleRepository.findByRole(Role.RoleName.USER);
        Role defaultRole = optionalRole.orElseThrow(
                () -> new IllegalStateException("Default role not found"));
        user.setRoles(Collections.singleton(defaultRole));
        User savedUser = userRepository.save(user);
        shoppingCartService.createForUser(savedUser);
        return userMapper.toDto(savedUser);
    }
}
