package edu.sharif.cc.controllers;

import edu.sharif.cc.services.ProblemService;
import edu.sharif.cc.services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final ProblemService problemService;
    private final UserService userService;

    public AuthController(ProblemService problemService, UserService userService) {
        this.problemService = problemService;
        this.userService = userService;
    }

    // Add a new user (signup)
//    @PostMapping(path = "/signup")
//    public UserDto signup(@RequestBody UserDto user) throws UserAlreadyExistsException {
//        return userService.createUser(user);
//    }

    // Login check
//    @PostMapping("/login")
//    public UserDto login(@RequestBody UserDto user) throws UserNotFoundException, InvalidCredentialsException {
//        return userService.login(user);
//    }

}
