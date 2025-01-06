package edu.sharif.cc.controllers;

import edu.sharif.cc.dtos.TeacherDTO;
import edu.sharif.cc.exceptions.UserNotFoundException;
import edu.sharif.cc.services.ProblemService;
import edu.sharif.cc.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {
    private final ProblemService problemService;
    private final UserService userService;

    public TeacherController(ProblemService problemService, UserService userService) {
        this.problemService = problemService;
        this.userService = userService;
    }

    // Get all teachers
    @GetMapping(path = "/teachers")
    public List<TeacherDTO> getAllTeachers() {
        return userService.getAllTeachers();
    }

    // Get a teacher by username
    @GetMapping(path = "/teachers/{username}")
    public TeacherDTO getTeacherByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        return userService.getTeacherByUsername(username);
    }

}
