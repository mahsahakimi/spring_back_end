package edu.sharif.cc.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {

    //    private Long id;
    private String username;
    private String name;
    private Integer created;

    public TeacherDTO(String username, String name, Integer created) {
        this.name = name;
        this.username = username;
        this.created = created;
    }
}
