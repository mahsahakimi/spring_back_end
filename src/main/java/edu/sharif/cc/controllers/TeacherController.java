package edu.sharif.cc.controllers;

import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.dtos.StudentDTO;
import edu.sharif.cc.dtos.TeacherDTO;
import edu.sharif.cc.services.ProblemService;
import edu.sharif.cc.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final ProblemService problemService;
    private final TeacherService teacherService;

    public TeacherController(ProblemService problemService, TeacherService teacherService) {
        this.problemService = problemService;
        this.teacherService = teacherService;
    }

    // Add a new teacher
    @PostMapping
    public void saveTeacher(@RequestBody TeacherDTO teacher) {
        teacherService.saveTeacher(teacher);
    }

    // Get all teachers
    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // Get a teacher by username
    @GetMapping(path = "/{username}")
    public TeacherDTO getTeacherByUsername(@PathVariable("username") String username) {
        return teacherService.getTeacherByUsername(username);
    }

    // Get a teacher's created problems by username
    @GetMapping("/{username}/created")
    public List<ProblemDTO> getCreatedProblemsByTeacher(@PathVariable("username") String username) {
        return teacherService.getCreatedProblems(username);
    }

    // Get a teacher's followers(students) by username
    @GetMapping("/{username}/followers/students")
    public List<StudentDTO> getFollowersStudents(@PathVariable("username") String username) {
        return teacherService.getFollowersStudents(username);
    }

    // Get a teacher's followers(teachers) by username
    @GetMapping("/{username}/followers/teachers")
    public List<TeacherDTO> getFollowers(@PathVariable("username") String username) {
        return teacherService.getFollowersTeachers(username);
    }

    // Add a created question for a teacher
    @PostMapping("/{username}/created")
    public ResponseEntity<?> addCreatedProblem(@PathVariable String username, @RequestParam String title) {
        teacherService.addCreatedProblem(username, title);
        return ResponseEntity.ok().build();
    }

    // Get a teacher's followings(teacher) by username
    @GetMapping("/{username}/followings")
    public List<TeacherDTO> getFollowings(@PathVariable("username") String username) {
    return teacherService.getFollowings(username);
    }

    // Add a teacher to followings by username
    @PostMapping("/{username}/follow")
    public ResponseEntity<?> followTeacher(@PathVariable String username, @RequestParam String teacherUsername) {
        teacherService.followTeacher(username, teacherUsername);
        return ResponseEntity.ok().build();
    }
}
