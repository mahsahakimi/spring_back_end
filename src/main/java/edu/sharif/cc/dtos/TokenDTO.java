package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String username;
    private String password;
    private String type;

    public TokenDTO(String username, String password, String type) {
        this.username = username;
        this.type = type;
        this.password = password;
    }
}
