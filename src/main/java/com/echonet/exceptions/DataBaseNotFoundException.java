package com.echonet.exceptions;

/**
 * Exception that should be thrown if the database cannot be found, or is inaccessable. 
 */
public class DataBaseNotFoundException extends Exception {
    public DataBaseNotFoundException() {}
    public DataBaseNotFoundException(String message) {super(message);}
}
