package com.cms.service;

import com.cms.dto.UserDto;
import com.cms.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    UserDto getById(Long id);

    List<UserDto> getAll();

    User deleteUser(Long id);
}
