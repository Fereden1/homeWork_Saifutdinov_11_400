package com.saifutdinov.service;

import com.saifutdinov.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
}