package ru.gb.java9_78.controller;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
