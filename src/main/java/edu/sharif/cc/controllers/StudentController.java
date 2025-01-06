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
public class StudentController {
    private final ProblemService problemService;
    private final UserService userService;

    public StudentController(ProblemService problemService, UserService userService) {
        this.problemService = problemService;
        this.userService = userService;
    }

    // Get all students
    @GetMapping(path = "/students")
    public List<StudentDTO> getAllStudents() {
        return userService.getAllStudents();
    }

    // Get a student by username
    @GetMapping(path = "/students/{username}")
    public StudentDTO getStudentByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        return userService.getStudentByUsername(username);
    }

    // Get a student's solved problems by username
    @GetMapping(path = "/students/{username}/solved")
    public List<ProblemDTO> getSolvedProblemsByStudent(@PathVariable("username") String username) throws UserNotFoundException {
        return userService.getSolvedProblemsByStudent(username);
    }

    // Add a solved question for a student
//    @PutMapping("/addsolved")
//    public ResponseEntity<String> addSolvedProblem(@RequestBody AddSolvedProblemRequest request) {
//        studentService.addSolvedProblem(request);
//        return ResponseEntity.ok("Problem added to solved list");
//    }
}
