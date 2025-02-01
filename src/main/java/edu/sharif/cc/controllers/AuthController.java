package edu.sharif.cc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.sharif.cc.dtos.SignInRequestDTO;
import edu.sharif.cc.dtos.SignUpRequestDTO;
import edu.sharif.cc.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup/student")
    public String studentSignUp(@RequestBody SignUpRequestDTO signUpRequest) {
        return authenticationService.studentSignUp(signUpRequest);
    }

    @PostMapping("/signin/student")
    public String studentSignIn(@RequestBody SignInRequestDTO signInRequest) {
        return authenticationService.studentSignIn(signInRequest);
    }
    @PostMapping("/signup/teacher")
    public String teacherSignUp(@RequestBody SignUpRequestDTO signUpRequest) {
        return authenticationService.teacherSignUp(signUpRequest);
    }

    @PostMapping("/signin/teacher")
    public String teacherSignIn(@RequestBody SignInRequestDTO signInRequest) {
        return authenticationService.teacherSignIn(signInRequest);
    }
}

