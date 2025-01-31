package edu.sharif.cc.controllers;
import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.dtos.StudentDTO;
import edu.sharif.cc.dtos.TeacherDTO;
import edu.sharif.cc.services.ProblemService;
import edu.sharif.cc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

     // Get all students
     @GetMapping
     public List<StudentDTO> getAllStudents() {
         return studentService.getAllStudents();
     }

     // Add a new student
     @PostMapping
     public void saveStudent(@RequestBody StudentDTO student) {
         studentService.saveStudent(student);
     }

     // Get a student by username
     @GetMapping("/{username}")
     public StudentDTO getStudentByUsername(@PathVariable("username") String username) {
         return studentService.getStudentByUsername(username);
     }

     // Get a student's solved problems by username
     @GetMapping("/{username}/solved")
     public List<ProblemDTO> getSolvedProblemsByStudent(@PathVariable("username") String username) {
         return studentService.getSolvedProblems(username);
     }

     // Add a solved question for a student
     @PostMapping("/{username}/solved")
     public ResponseEntity<?> addSolvedProblem(@PathVariable String username, @RequestParam String title) {
         studentService.addSolvedProblem(username, title);
         return ResponseEntity.ok().build();
     }

     // Get a student's followers(students) by username
     @GetMapping("/{username}/followers")
     public List<StudentDTO> getFollowers(@PathVariable("username") String username) {
         return studentService.getFollowers(username);
     }

     // Get a student's followings(students) by username
     @GetMapping("/{username}/followings/students")
     public List<StudentDTO> getFollowingsStudents(@PathVariable("username") String username) {
         return studentService.getFollowingsStudents(username);
     }

    // Get a student's followings(teachers) by username
    @GetMapping("/{username}/followings/teachers")
    public List<TeacherDTO> getFollowingsTeachers(@PathVariable("username") String username) {
        return studentService.getFollowingsTeachers(username);
     }

     // Add a student to student's followings by username
     @PostMapping("/{username}/follow/student")
     public ResponseEntity<?> followStudent(@PathVariable String username, @RequestParam String studentUsername) {
         studentService.followStudent(username, studentUsername);
         return ResponseEntity.ok().build();
     }

     // Add a teacher to student's followings by username
     @PostMapping("/{username}/follow/teacher")
     public ResponseEntity<?> followTeacher(@PathVariable String username, @RequestParam String teacherUsername) {
         studentService.followTeacher(username, teacherUsername);
         return ResponseEntity.ok().build();
     }
}
