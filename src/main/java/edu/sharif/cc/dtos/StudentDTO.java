package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

//    private Long id;
    private String username;
    private String name;
    private Integer score;

    public StudentDTO(String username, String name, Integer score) {
        this.username = username;
        this.score = score;
        this.name = name;
    }
}
