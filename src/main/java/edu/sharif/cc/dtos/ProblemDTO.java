package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemDTO {

//    private Long id;
    private String title;
    private String author;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    public ProblemDTO(String title, String author, String answer, String option1, String option2, String option3, String option4) {
        this.title = title;
        this.author = author;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
    public ProblemDTO() {
    }
}
