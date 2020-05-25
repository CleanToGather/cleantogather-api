package com.yellowstone.cleantogather.api.user;

import com.yellowstone.cleantogather.api.common.exception.NotFoundException;

import com.yellowstone.cleantogather.api.security.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means this class is a rest controller
@RequestMapping(path="/users") // This means URL's start with /events (after Application path)
@CrossOrigin
public class UserController {
	
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper mapper, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signin")
    @ApiOperation(value = "Authenticates user and returns its JWT token")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"), @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(@ApiParam("Name") @RequestParam String name, @ApiParam("Password") @RequestParam String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
            return jwtTokenProvider.createToken(name, userRepository.findByName(name).getRoles());
        } catch (AuthenticationException e) {
            throw e;
        }
    }

    @ApiOperation("Create a new user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public User postUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    
    @ApiOperation("Get all the users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    
    @ApiOperation("Get an user by his id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    
    @ApiOperation("Patch an existent user")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public User patchUser(@RequestBody User user, @PathVariable("id") Long id) {
        User userToUpdate = userRepository.findById(id).orElseThrow(NotFoundException::new);
        mapper.map(user, userToUpdate);
        return userRepository.save(userToUpdate);
    }

    @ApiOperation("Delete an user")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") Long id) {
        User deletedUser = userRepository.findById(id).orElseThrow(NotFoundException::new);
        userRepository.deleteById(id);
        return deletedUser;
    }
}