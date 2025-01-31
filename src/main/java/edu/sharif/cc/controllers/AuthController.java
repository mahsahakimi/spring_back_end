package edu.sharif.cc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.sharif.cc.dtos.SignInRequestDTO;
import edu.sharif.cc.dtos.SignUpRequestDTO;
import edu.sharif.cc.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDTO signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody SignInRequestDTO signInRequest) {
        return authenticationService.signIn(signInRequest);
    }
}

