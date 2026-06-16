package com.cms.service;

import com.cms.dto.RegisterUser;
import com.cms.entity.User;

public interface UserService {

    User registerUser(RegisterUser registerUser);
}
