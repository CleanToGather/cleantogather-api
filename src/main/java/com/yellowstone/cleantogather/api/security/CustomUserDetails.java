package com.yellowstone.cleantogather.api.security;

import com.yellowstone.cleantogather.api.user.User;
import com.yellowstone.cleantogather.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        final User user = userRepository.findByName(name);

        if (user == null) {
            throw new UsernameNotFoundException("Name: '" + name + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(name)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
