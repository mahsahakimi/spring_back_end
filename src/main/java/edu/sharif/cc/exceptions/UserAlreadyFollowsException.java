package edu.sharif.cc.exceptions;

public class UserAlreadyFollowsException extends RuntimeException {
    public UserAlreadyFollowsException(String message) {
        super(message);
    }
}
