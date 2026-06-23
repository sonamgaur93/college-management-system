package com.cms.serviceImpl;

import com.cms.dao.UserDao;
import com.cms.dto.UserDto;
import com.cms.entity.User;
import com.cms.exception.GenericException;
import com.cms.mapper.UserMapper;
import com.cms.repository.UserRepository;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public User registerUser(UserDto userDto) {
        return userMapper.toEntity(userDto);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new
                GenericException("User id does not exist", HttpStatus.NOT_FOUND));

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setUsername(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new
                GenericException("User id does not exist", HttpStatus.NOT_FOUND));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll(String search, Boolean status, int page, int size, String sortBy, String sortOrder) {
        List<User> users = userDao.findAllUsers(search, status, page, size, sortBy, sortOrder);
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = userMapper.toDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }


    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new
                GenericException("User id does not exist", HttpStatus.NOT_FOUND));

        userRepository.delete(user);
    }
}
