package com.project.service;

import com.project.data.UserData;
import com.project.dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
    UserData findUserByEmail(String email);
}
