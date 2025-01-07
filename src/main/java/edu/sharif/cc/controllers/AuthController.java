package edu.sharif.cc.controllers;

import edu.sharif.cc.dtos.TokenDTO;
import edu.sharif.cc.dtos.UserDTO;
import edu.sharif.cc.exceptions.InvalidCredentialsException;
import edu.sharif.cc.exceptions.UserAlreadyExistsException;
import edu.sharif.cc.exceptions.UserNotFoundException;
import edu.sharif.cc.services.ProblemService;
import edu.sharif.cc.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final ProblemService problemService;
    private final UserService userService;

    public AuthController(ProblemService problemService, UserService userService) {
        this.problemService = problemService;
        this.userService = userService;
    }

    // added to front-end @ Signup.js
    // Add a new user (signup)
    @PostMapping(path = "/signup")
    public TokenDTO signup(@RequestBody UserDTO user) throws UserAlreadyExistsException {
        return userService.createUser(user);
    }

    // Login check
//    @PostMapping("/login")
//    public TokenDTO login(@RequestBody TokenDTO token) throws UserNotFoundException, InvalidCredentialsException {
//        return userService.login(token);
//    }

}
