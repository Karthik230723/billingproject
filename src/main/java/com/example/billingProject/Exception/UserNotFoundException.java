package com.example.billingProject.Exception;

import java.util.NoSuchElementException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
        System.out.println(message);

    }
}