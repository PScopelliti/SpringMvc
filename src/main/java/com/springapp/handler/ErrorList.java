package com.springapp.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains a list of error field.
 */
public class ErrorList {

    private List<ErrorField> errorFields = new ArrayList<>();

    public ErrorList() {

    }

    public void addFieldError(final String path, final String message) {
        ErrorField error = new ErrorField(path, message);
        errorFields.add(error);
    }

    public List<ErrorField> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(final List<ErrorField> errorFields) {
        this.errorFields = errorFields;
    }
}
