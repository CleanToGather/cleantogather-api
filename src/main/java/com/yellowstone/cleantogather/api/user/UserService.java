package com.yellowstone.cleantogather.api.user;

import com.yellowstone.cleantogather.api.common.exception.UnprocessableEntityException;
import com.yellowstone.cleantogather.api.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signin(String name, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
            return jwtTokenProvider.createToken(name, userRepository.findByName(name).getRoles());
        } catch (AuthenticationException e) {
            throw new UnprocessableEntityException();
        }
    }

    public String signup(User user) {
        if (userRepository.findByName(user.getName()) == null && userRepository.findByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getName(), user.getRoles());
        } else {
            return "Username or email already in use";
        }
    }

    /* Maybe for later
    public String refresh(String name) {
        return jwtTokenProvider.createToken(name, userRepository.findByName(name).getRoles());
    }*/
}
