package com.demo.travelinsurance.exception;


public class PolicyCreationException extends Exception {

    public PolicyCreationException(String message) {
        super(message);
    }

    public PolicyCreationException(String message, Throwable cause) {
        super(message, cause);


    }
}

