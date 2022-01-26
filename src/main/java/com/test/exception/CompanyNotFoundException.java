package com.test.exception;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(String id) {
        super("Could not find Company " + id);
    }
}
