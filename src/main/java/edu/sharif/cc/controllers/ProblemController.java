package edu.sharif.cc.controllers;
import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.services.ProblemService;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    // added to front-end @ Problems.js
    // postman checked
    // Get all problems
    @GetMapping(path = "/problems")
    public List<ProblemDTO> getAllProblems() {

        return problemService.getAllProblems();
    }

    // added to front-end @ Problems.js
    // postman checked
    // Get all problems by author
    @GetMapping(path = "/problems/author/{author}")
    public List<ProblemDTO> getProblemsByAuthor(@PathVariable("author") String author) {
        return problemService.getProblemsByAuthor(author);
    }

    // added to front-end @ ProblemReaderView.js
    // Get a problem by title
    @GetMapping(path = "/problems/{title}")
    public ProblemDTO getProblemByTitle(@PathVariable("title") String title) {
        return problemService.getProblemByTitle(title);
    }

    // @ Problems.js
    // PATH CHANGED!!!!
    // /problems/checkproblem/:username
    // Check problem answer
    // @PostMapping("/problems/check/{username}")
    // public ResponseEntity<String> checkProblemAnswer(@PathVariable("username") String username, @RequestBody CheckAnswerRequest request) throws ProblemNotFoundException, UserNotFoundException {
    //     boolean isCorrect = problemService.checkProblemAnswer(username, request);

    //     if (isCorrect) {
    //         return ResponseEntity.ok("Correct answer!");
    //     } else {
    //         return ResponseEntity.ok("Incorrect answer. Try again.");
    //     }
    // }

    // added to front-end @ Problems.js
    // postman checked
    // Add a new problem
    @PostMapping(path = "/problems")
    public void saveProblem(@RequestBody ProblemDTO problem) {
        problemService.saveProblem(problem);
    }

    // Update a problem
//    @PutMapping("/updateproblem/{title}")
//    public ResponseEntity<Problem> updateProblem(
//            @PathVariable String title,
//            @RequestBody UpdateProblemRequest updateRequest) {
//        Problem updatedProblem = problemService.updateProblem(title, updateRequest);
//        return ResponseEntity.ok(updatedProblem);
//    }
}
