package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    //    private Long id;
    private String username;
    private String name;
    private String password;
    private String type;
    private String score;
    private String followers;
    private String followings;

    public UserDTO(String username, String password, String type, String name) {
        this.username = username;
        this.type = type;
        this.name = name;
        this.password = password;
    }

    public UserDTO(String followings, String score, String followers, String type, String password, String name, String username) {
        this.followings = followings;
        this.score = score;
        this.followers = followers;
        this.type = type;
        this.password = password;
        this.name = name;
        this.username = username;
    }
}