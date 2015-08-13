package com.springapp.handler;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class contains a list of error field.
 */
public class ErrorList {

    private Collection<Error> errors = new ArrayList<>();

    public ErrorList() {

    }

    public void addFieldError(final String path, final String message) {
        Error error = new Error(path, message);
        errors.add(error);
    }

    public Collection<Error> getErrors() {
        return errors;
    }

    public void setErrors(final Collection<Error> errors) {
        this.errors = errors;
    }
}
