package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String username;
    private String password;
    private String name;

    public SignUpRequestDTO(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }
}