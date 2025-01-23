package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProblemDTO {

//    private Long id;
    private String title;
    private String content;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
    private String answer;
    private String difficulty;
    private String category;
    private String author;
    private String solved;

    public ProblemDTO(String title, String content, String option_1, String option_2, String option_3, String option_4, String answer, String difficulty, String category, String author, String solved) {
        this.title = title;
        this.content = content;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
        this.option_4 = option_4;
        this.answer = answer;
        this.difficulty = difficulty;
        this.solved = solved;
        this.author = author;
        this.category = category;
    }
}
