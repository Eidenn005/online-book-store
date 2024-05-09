package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.UserRegistrationRequestDto;
import com.example.onlinebookstore.dto.UserResponseDto;
import com.example.onlinebookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto save(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
