package edu.sharif.cc.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckAnswerRequest {

    // Getters and Setters
    private String problemTitle;
    private String answer;

    // Optional Constructor
    public CheckAnswerRequest(String problemTitle, String answer) {
        this.problemTitle = problemTitle;
        this.answer = answer;
    }

    // No-arg Constructor for frameworks like Jackson
    public CheckAnswerRequest() {
    }
}
