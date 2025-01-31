package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDTO {
    private String username;
    private String password;

    public SignInRequestDTO(String username, String password){
        this.username = username;
        this.password = password;
    }
}
