package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemDTO {

//    private Long id;
    private String title;
    private String author;
    private String answer;

    public ProblemDTO(String title, String author, String answer) {
        this.title = title;
        this.author = author;
        this.answer = answer;
    }
    public ProblemDTO() {
    }
}
