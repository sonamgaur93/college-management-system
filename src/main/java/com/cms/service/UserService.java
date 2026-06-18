package com.cms.service;

import com.cms.dto.UserDto;
import com.cms.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    UserDto getById(Long id);

    List<UserDto> getAll(String search, Boolean status, int page, int size, String sortBy, String sortOrder);

    User deleteUser(Long id);
}
