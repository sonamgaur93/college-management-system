package com.cms.serviceImpl;

import com.cms.dto.RegisterUser;
import com.cms.entity.User;
import com.cms.repository.UserRepository;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUser registerUser) {
        User user = new User();

        user.setFullName(registerUser.getFullName());
        user.setEmail(registerUser.getEmail());
        user.setRole(registerUser.getRole());
        user.setUsername(registerUser.getEmail());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        return userRepository.save(user);
    }
}
