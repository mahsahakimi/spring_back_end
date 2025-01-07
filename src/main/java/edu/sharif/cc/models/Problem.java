package edu.sharif.cc.models;

import edu.sharif.cc.dtos.ProblemDTO;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "Problem")
@Table(name = "problem")
@Getter
@Setter
@NoArgsConstructor
public class Problem {

    @Id
    @SequenceGenerator(name = "problem_id_sequence", sequenceName = "actual_problem_id_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problem_id_sequence")
    private Long id;

    @Column(name = "title", columnDefinition = "TEXT", nullable = false, unique = true)
    private String title;

    @Column(name = "author", columnDefinition = "TEXT", nullable = false)
    private String author;

    @Column(name = "option_1", columnDefinition = "TEXT", nullable = false)
    private String option1;

    @Column(name = "option_2", columnDefinition = "TEXT", nullable = false)
    private String option2;

    @Column(name = "option_3", columnDefinition = "TEXT", nullable = false)
    private String option3;

    @Column(name = "option_4", columnDefinition = "TEXT", nullable = false)
    private String option4;

    @Column(name = "answer", columnDefinition = "TEXT", nullable = false)
    private String answer;

    @ManyToMany(mappedBy = "solvedProblems")
    private List<Student> solvedBy;

    public Problem(String title, String author, String answer, String option1, String option2, String option3, String option4) {
        this.title = title;
        this.author = author;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public static Problem fromDto(ProblemDTO problemDto) {
        return new Problem(problemDto.getTitle(), problemDto.getAuthor(), problemDto.getAnswer(),
                problemDto.getOption1(), problemDto.getOption2(), problemDto.getOption3(), problemDto.getOption4());
    }

    public static ProblemDTO toDto(Problem problem) {
        return new ProblemDTO(problem.getTitle(), problem.getAuthor(), problem.getAnswer(),
                problem.getOption1(), problem.getOption2(), problem.getOption3(), problem.getOption4());
    }
}
