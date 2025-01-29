package edu.sharif.cc.controllers;

import edu.sharif.cc.dtos.TokenDTO;
import edu.sharif.cc.dtos.UserDTO;
import edu.sharif.cc.services.ProblemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final ProblemService problemService;

    public AuthController(ProblemService problemService) {
        this.problemService = problemService;
    }

    // added to front-end @ Signup.js
    // Add a new user (signup)
    // @PostMapping(path = "/signup")
    // public TokenDTO signup(@RequestBody UserDTO user) {
    //     return userService.createUser(user);
    // }

    // Login check
//    @PostMapping("/login")
//    public TokenDTO login(@RequestBody TokenDTO token) throws UserNotFoundException, InvalidCredentialsException {
//        return userService.login(token);
//    }

}
