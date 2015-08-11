package com.springapp.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains a list of error field.
 */
public class ErrorList {

    private List<Error> errors = new ArrayList<>();

    public ErrorList() {

    }

    public void addFieldError(final String path, final String message) {
        Error error = new Error(path, message);
        errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(final List<Error> errors) {
        this.errors = errors;
    }
}
