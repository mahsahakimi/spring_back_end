package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {

    //    private Long id;
    private String username;
    private String name;

    public TeacherDTO(String name, String username) {
        this.name = name;
        this.username = username;
    }
}
