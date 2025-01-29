package edu.sharif.cc.models;

import edu.sharif.cc.dtos.ProblemDTO;
import edu.sharif.cc.enums.DifficultyLevel;
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

    @Column(name = "author", columnDefinition = "TEXT", nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "option_1", columnDefinition = "TEXT", nullable = false)
    private String option1;

    @Column(name = "option_2", columnDefinition = "TEXT", nullable = false)
    private String option2;

    @Column(name = "option_3", columnDefinition = "TEXT", nullable = false)
    private String option3;

    @Column(name = "option_4", columnDefinition = "TEXT", nullable = false)
    private String option4;

    @Column(name = "correct_answer_index", columnDefinition = "INTEGER", nullable = false)
    private Integer answer;

    @Column(name = "difficulty", nullable = false)
    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;

    @ManyToMany(mappedBy = "problems")
    private List<Category> categories;

    @ManyToMany(mappedBy = "solvedProblems")
    private List<Student> solvedBy;

    public Problem(String title, String content, String option1, String option2, String option3, String option4, Integer answer, DifficultyLevel difficulty, String author) {
        this.title = title;
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.difficulty = difficulty;
        this.author = author;
    }

    public boolean isCorrect(Integer answerIndex) {
        return answer.equals(answerIndex);
    }

    public static Problem fromDto(ProblemDTO problemDto) {
        return new Problem(problemDto.getTitle(), problemDto.getContent(), problemDto.getOption1(), problemDto.getOption2(), problemDto.getOption3(), problemDto.getOption4(), problemDto.getAnswer(), problemDto.getDifficulty(), problemDto.getAuthor());
    }

    public static ProblemDTO toDto(Problem problem) {
        return new ProblemDTO(problem.getTitle(), problem.getContent(), problem.getOption1(), problem.getOption2(), problem.getOption3(), problem.getOption4(), problem.getAnswer(), problem.getDifficulty(), problem.getAuthor());
    }
}
