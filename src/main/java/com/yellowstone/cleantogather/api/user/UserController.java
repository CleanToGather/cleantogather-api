package com.yellowstone.cleantogather.api.user;

import com.yellowstone.cleantogather.api.common.exception.NotFoundException;

import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means this class is a rest controller
@RequestMapping(path="/users") // This means URL's start with /events (after Application path)
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @ApiOperation("Create a new user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public User postUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    
    @ApiOperation("Get all the users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
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