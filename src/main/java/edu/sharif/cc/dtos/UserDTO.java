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

    public UserDTO(String username, String password, String type, String name) {
        this.username = username;
        this.type = type;
        this.name = name;
        this.password = password;
    }
}