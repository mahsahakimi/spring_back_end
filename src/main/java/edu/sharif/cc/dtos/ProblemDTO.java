package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemDTO {

//    private Long id;
    private String title;
    private String author;

    public ProblemDTO(String title, String author) {
        this.title = title;
        this.author = author;
    }
}