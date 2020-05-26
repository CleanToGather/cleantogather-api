package com.yellowstone.cleantogather.api.security;

import com.yellowstone.cleantogather.api.user.User;
import com.yellowstone.cleantogather.api.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController // This means this class is a rest controller
@RequestMapping(path="/") // This means URL's start with /events (after Application path)
@CrossOrigin
public class SecurityController {

    private final UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    @ApiOperation(value = "Authenticates user and returns its JWT token")
    public String login(@ApiParam("Name") @RequestParam String name, @ApiParam("Password") @RequestParam String password) {
        return userService.signin(name, password);
    }

    @ApiOperation("Create a new user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public String postUser(@RequestBody User user) {
        return userService.signup(user);
    }
}
