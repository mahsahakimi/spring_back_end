package edu.sharif.cc.controllers;
import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    // Get all problems
    @GetMapping
    public List<ProblemDTO> getAllProblems() {
        return problemService.getAllProblems();
    }

     // Get all problems by author
     @GetMapping("/author/{author}")
     public List<ProblemDTO> getProblemsByAuthor(@PathVariable("author") String teacherUsername) {
         return problemService.getProblemsByAuthor(teacherUsername);
     }

    // Get a problem by title
    @GetMapping("/{title}")
    public ProblemDTO getProblemByTitle(@PathVariable("title") String title) {
        return problemService.getProblemByTitle(title);
    }

    // Add a new problem
    @PostMapping
    public void saveProblem(@RequestBody ProblemDTO problem) {
        problemService.saveProblem(problem);
    }

    // Get all categories names
    @GetMapping("/category")
    public List<String> getAllCategories() {
        return problemService.getAllCategories();
    }

    // Add a new category
    @PostMapping("/category")
    public void saveCategory(@RequestParam String name) {
        problemService.saveCategory(name);
    }

    // Check problem answer
    @GetMapping("/{title}/check")
    public boolean checkAnswer(@PathVariable String title, @RequestParam Integer answerIndex) {
        return problemService.checkAnswer(title, answerIndex);
    }
//-------
    // Set a author for a problem
    @PostMapping("/{title}/author")
    public ResponseEntity<?> addTeacherToProblem(@PathVariable String title, @RequestParam String teacherUsername) {
        problemService.addTeacherToProblem(title, teacherUsername);
        return ResponseEntity.ok().build();
    }

    // Set a category to a problem
    @PostMapping("/{title}/category")
    public ResponseEntity<?> addCategoryToProblem(@PathVariable String title, @RequestParam String categoryName) {
        problemService.addCategoryToProblem(title, categoryName);
        return ResponseEntity.ok().build();
    }

    // Get all problems of a categories by categoryName
    @GetMapping(path = "/category/{categoryName}")
    public List<ProblemDTO> getAllProblemsByCategoryName(@PathVariable String categoryName) {
        return problemService.getAllProblemsByCategoryName(categoryName);
    }

    // Get category of a problem by title
    @GetMapping(path = "/{title}/category")
    public String getCategoryByTitle(@PathVariable String title) {
        return problemService.getCategoryByTitle(title);
    }
}
