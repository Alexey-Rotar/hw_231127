package org.example;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(String message){
        super(message);
    }
}