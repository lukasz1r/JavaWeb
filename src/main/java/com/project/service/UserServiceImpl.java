package com.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.data.RoleData;
import com.project.data.UserData;
import com.project.dto.UserDto;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        RoleData role = roleRepository.findByName("ROLE_USER");
        UserData user = new UserData(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserData findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
