package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDTO {

//    private Long id;
    private String name;
    private String score;
    private String username;
    private String followers;
    private String followings;
    private List<String> solved;

    public StudentDTO(List<String> solved, String followings, String followers, String username, String score, String name) {
        this.solved = solved;
        this.followings = followings;
        this.followers = followers;
        this.username = username;
        this.score = score;
        this.name = name;
    }
}
