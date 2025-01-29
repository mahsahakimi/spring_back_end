package edu.sharif.cc.controllers;

import edu.sharif.cc.dtos.TeacherDTO;
import edu.sharif.cc.services.ProblemService;
import edu.sharif.cc.services.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
