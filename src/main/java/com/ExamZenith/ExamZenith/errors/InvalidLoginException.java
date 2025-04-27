package com.ExamZenith.ExamZenith.errors;

public class InvalidLoginException extends RuntimeException{

    public InvalidLoginException(String message){
        super(message);
    }

}

