package edu.sharif.cc.services;

import edu.sharif.cc.Repository.CategoryRepository;
import edu.sharif.cc.Repository.ProblemRepository;
import edu.sharif.cc.Repository.StudentRepository;
import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.exceptions.*;
import edu.sharif.cc.models.Category;
import edu.sharif.cc.models.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;

     @Autowired
    public ProblemService(ProblemRepository problemRepository, StudentRepository studentRepository,  CategoryRepository categoryRepository) {
        this.problemRepository = problemRepository;
        this.studentRepository = studentRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProblemDTO> getAllProblems() {
        List<Problem> problems = problemRepository.findAll();
        return problems.stream()
                .map(problem -> Problem.toDto(problem))
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> category.getName())
                .collect(Collectors.toList());
    }

    public List<ProblemDTO> getProblemsByAuthor(String author) {
        List<Problem> problems = problemRepository.findByAuthor(author);
        return problems.stream()
                .map(problem -> Problem.toDto(problem))
                .collect(Collectors.toList());
    }

    public List<String> getAllCategoriesByTitle(String title) {
        Problem problem = problemRepository.findByTitle(title)
                .orElseThrow(() -> new ProblemNotFoundException("Problem with title '" + title + "' not found."));
        return problem.getCategories().stream()
                .map(category -> category.getName())
                .collect(Collectors.toList());
    }

    public List<ProblemDTO> getAllProblemsByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name '" + categoryName + "' not found."));
        return category.getProblems().stream()
                .map(problem -> Problem.toDto(problem))
                .collect(Collectors.toList());
    }

    public ProblemDTO getProblemByTitle(String title) {
        Problem problem = problemRepository.findByTitle(title)
                .orElseThrow(() -> new ProblemNotFoundException("Problem with title '" + title + "' not found."));
        return Problem.toDto(problem);
    }

    public void saveProblem(ProblemDTO problemDto) {
        Problem problem = Problem.fromDto(problemDto);
        if (problemRepository.existsByTitle(problem.getTitle())) {
            throw new ProblemAlreadyExistsException("Problem with title '" + problem.getTitle() + "' already exists.");
        }
        problemRepository.save(problem);
    }

    public void saveCategory(String name) {
        Category category = new Category(name);
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException("Category with name '" + category.getName() + "' already exists.");
        }
        categoryRepository.save(category);
    }

    public void addCategoryToProblem(String title, String categoryName) {
        Problem problem = problemRepository.findByTitle(title)
                .orElseThrow(() -> new ProblemNotFoundException("Problem with title '" + title + "' not found."));

        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name '" + categoryName + "' not found."));

        if (!(problem.getCategories().contains(category))) {
            problem.getCategories().add(category);
            problemRepository.save(problem);
        }
    }

    public void removeCategoryFromProblem(String title, String categoryName) {
        Problem problem = problemRepository.findByTitle(title)
                .orElseThrow(() -> new ProblemNotFoundException("Problem with title '" + title + "' not found."));

        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name '" + categoryName + "' not found."));

        if (problem.getCategories().contains(category)) {
            problem.getCategories().remove(category);
            problemRepository.save(problem);
        }
    }

    public boolean checkAnswer(String title, Integer answerIndex) {
        Problem problem = problemRepository.findByTitle(title)
                .orElseThrow(() -> new ProblemNotFoundException("Problem with title '" + title + "' not found."));

        return problem.isCorrect(answerIndex);
    }
}
