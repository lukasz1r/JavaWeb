package com.project.service;

import com.project.data.RoleData;
import com.project.data.UserData;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserData user = userRepository.findByEmail(email);
        if (user != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            for (RoleData role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return new User(
                    user.getEmail(), 
                    user.getPassword(),
                    authorities);
        } else {
            throw new UsernameNotFoundException("Niepoprawne dane");
        }
    }
}
