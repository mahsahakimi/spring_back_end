package edu.sharif.cc.dtos;

import edu.sharif.cc.enums.DifficultyLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProblemDTO {

    // private Long id;
    private String title;
    private String content;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer answer;
    private DifficultyLevel difficulty;

    public ProblemDTO(String title, String content, String option1, String option2, String option3, String option4, Integer answer, DifficultyLevel difficulty) {
        this.title = title;
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.difficulty = difficulty;
    }
}
