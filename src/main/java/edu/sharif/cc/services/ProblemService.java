package edu.sharif.cc.services;

import edu.sharif.cc.Repository.ProblemRepository;
import edu.sharif.cc.Repository.StudentRepository;
import edu.sharif.cc.dtos.CheckAnswerRequest;
import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.exceptions.ProblemAlreadyExistsException;
import edu.sharif.cc.exceptions.ProblemNotFoundException;
import edu.sharif.cc.exceptions.UserNotFoundException;
import edu.sharif.cc.models.Problem;
import edu.sharif.cc.models.Student;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final StudentRepository studentRepository;

    // @Autowired
    public ProblemService(ProblemRepository problemRepository, StudentRepository studentRepository) {
        this.problemRepository = problemRepository;
        this.studentRepository = studentRepository;
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

    public boolean checkProblemAnswer(String username, CheckAnswerRequest request)
            throws ProblemNotFoundException, UserNotFoundException {

        // Check if the student exists
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        // Check if the problem exists
        Problem problem = problemRepository.findByTitle(request.getProblemTitle())
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found with title: " + request.getProblemTitle()));

        // Validate the answer
        boolean isCorrect = problem.getAnswer().equalsIgnoreCase(request.getAnswer());

        // If correct, update the student's solved problems
//        if (isCorrect) {
//            if (!student.getSolved().contains(problem.getTitle())) {
//                student.getSolved().add(problem.getTitle());
//                StudentRepository.save(student); // Persist the changes
//            }
//        }

        return isCorrect;
    }}
