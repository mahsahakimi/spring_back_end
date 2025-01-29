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
    @SequenceGenerator(name = "problem_id_sequence", sequenceName = "actual_problem_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problem_id_sequence")
    private Long id;

    @Column(name = "title", columnDefinition = "TEXT", nullable = false, unique = true)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
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

    @Column(name = "difficulty", columnDefinition = "TEXT", nullable = false)
    private String difficulty;

    @Column(name = "category", columnDefinition = "TEXT", nullable = false)
    private String category;

    @Transient
    private Integer solved;

    public Integer getSolved() {
        return solvedBy.size();
    }

    @ManyToMany(mappedBy = "solvedProblems")
    private List<Student> solvedBy;

    public Problem(String title, String content, String option1, String option2, String option3, String option4, String answer, String difficulty, String category, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.difficulty = difficulty;
        this.category = category;
    }

    public static Problem fromDto(ProblemDTO problemDto) {
        return new Problem(problemDto.getTitle(), problemDto.getContent(), problemDto.getOption_1(), problemDto.getOption_2(), problemDto.getOption_3(), problemDto.getOption_4(), problemDto.getAnswer(), problemDto.getDifficulty(), problemDto.getCategory(), problemDto.getAuthor());

    }

    public static ProblemDTO toDto(Problem problem) {
        return new ProblemDTO(problem.getTitle(), problem.getContent(), problem.getOption1(), problem.getOption2(), problem.getOption3(), problem.getOption4(), problem.getAnswer(), problem.getDifficulty(), problem.getCategory(), problem.getAuthor(), Integer.toString(problem.getSolved()));
    }
}
