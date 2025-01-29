package edu.sharif.cc.controllers;

import edu.sharif.cc.services.ProblemService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final ProblemService problemService;

    public AuthController(ProblemService problemService) {
        this.problemService = problemService;
    }

//     Signup
//     @PostMapping(path = "/signup")
//     public TokenDTO signup(@RequestBody UserDTO user) {
//         return userService.createUser(user);
//     }

//     Login
//    @PostMapping("/login")
//    }

}
