package edu.sharif.cc.controllers;

import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.dtos.StudentDTO;
import edu.sharif.cc.dtos.TeacherDTO;
import edu.sharif.cc.exceptions.ProblemAlreadyExistsException;
import edu.sharif.cc.exceptions.ProblemNotFoundException;
import edu.sharif.cc.exceptions.UserNotFoundException;
import edu.sharif.cc.services.ProblemService;
import edu.sharif.cc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1")
public class ProblemController {

    private final ProblemService problemService;
    private final UserService userService;

     @Autowired
    public ProblemController(ProblemService problemService, UserService userService) {
        this.problemService = problemService;
        this.userService = userService;
    }

    // Get all problems
    @GetMapping(path = "/problems")
    public List<ProblemDTO> getAllProblems() {

        return problemService.getAllProblems();
    }

    // PATH CHANGED!!!!
    // /problems/:author
    // Get all problems by author
    @GetMapping(path = "/problems/author/{author}")
    public List<ProblemDTO> getProblemsByAuthor(@PathVariable("author") String author) {
        return problemService.getProblemsByAuthor(author);
    }

    // Get a problem by title
    @GetMapping(path = "/problems/{title}")
    public ProblemDTO getProblemByTitle(@PathVariable("title") String title) throws ProblemNotFoundException {
        return problemService.getProblemByTitle(title);
    }


    // PATH CHANGED!!!!
    // /problems/checkproblem/:username
    // Check problem answer
//    @PostMapping("/problems/check/{username}")
//    public void checkProblemAnswer(@PathVariable String username, @RequestBody CheckAnswerRequest request) throws ProblemNotFoundException, UserNotFoundException {
//        return problemService.checkProblemAnswer(username, request);
//    }

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

    // PATH CHANGED!!!!
    // /saveproblem
    // Add a new problem
    @PostMapping(path = "/problems")
    public void saveProblem(@RequestBody ProblemDTO problem) throws ProblemAlreadyExistsException {
        problemService.saveProblem(problem);
    }
}
