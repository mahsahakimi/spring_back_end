package edu.sharif.cc.models;

import edu.sharif.cc.dtos.ProblemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "Problem")
@Table(name = "problem")
@Getter
@Setter
public class Problem {
    @Id
    @SequenceGenerator(
            name = "problem_id_sequence",
            sequenceName = "actual_problem_id_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "problem_id_sequence"
    )
    private Long id;

    @Column(
            name = "title",
            columnDefinition = "TEXT",
            nullable = false,
            unique = true
    )
    private String title;

    @Column(
            name = "author",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String author;

    @ManyToMany(mappedBy = "solvedProblems")
    private List<Student> solvedBy;

    private String answer;

    public Problem() { // for JPA
    }

    public Problem(String title, String author, String answer) {
        this.title = title;
        this.author = author;
        this.answer = answer;
    }

    public static Problem fromDto(ProblemDTO problemDto) {
        return new Problem(problemDto.getTitle(), problemDto.getAuthor(), problemDto.getAnswer());
    }

    public static ProblemDTO toDto(Problem problem) {
        return new ProblemDTO(problem.getTitle(), problem.getAuthor(), problem.getAnswer());
    }
}
