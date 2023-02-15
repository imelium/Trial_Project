package com.avtukhov.trial_project.util.userError;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException(String msg){
        super(msg);
    }
}
