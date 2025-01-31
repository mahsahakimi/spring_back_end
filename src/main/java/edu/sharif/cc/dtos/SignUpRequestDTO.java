package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String username;
    private String password;

    public SignUpRequestDTO(String username, String password){
        this.username = username;
        this.password = password;
    }
}