package edu.sharif.cc.services;

import edu.sharif.cc.Repository.ProblemRepository;
import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.exceptions.ProblemAlreadyExistsException;
import edu.sharif.cc.exceptions.ProblemNotFoundException;
import edu.sharif.cc.exceptions.UserNotFoundException;
import edu.sharif.cc.models.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<ProblemDTO> getAllProblems() {
        List<Problem> problems = problemRepository.findAll();
        return problems.stream()
                .map(problem -> Problem.toDto(problem))
                .collect(Collectors.toList());
    }

    public List<ProblemDTO> getProblemsByAuthor(String author) {
        List<Problem> problems = problemRepository.findByAuthor(author);
        return problems.stream()
                .map(problem -> Problem.toDto(problem))
                .collect(Collectors.toList());
    }

    public ProblemDTO getProblemByTitle(String title) throws ProblemNotFoundException {
        Problem problem = problemRepository.findByTitle(title)
                .orElseThrow(
                        () -> new ProblemNotFoundException("Problem with title '" + title + "' not found.")
                );
        return Problem.toDto(problem); // Convert to DTO
    }

//    public void checkProblemAnswer(String username, CheckAnswerRequest request) throws ProblemNotFoundException, UserNotFoundException {
//        // Implement logic to check the answer using username, request data
//        // Throw exceptions if necessary
//    }
//

    public void saveProblem(ProblemDTO problemDto) throws ProblemAlreadyExistsException {
        Problem problem = Problem.fromDto(problemDto);
        if (problemRepository.existsByTitle(problem.getTitle())) {
            throw new ProblemAlreadyExistsException("Problem with title '" + problem.getTitle() + "' already exists.");
        }
        problemRepository.save(problem);
    }
}
