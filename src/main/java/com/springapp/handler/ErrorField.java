package com.springapp.handler;

/**
 * This class contains error field.
 */
public class ErrorField {

    private String field;

    private String message;

    public ErrorField(final String field, final String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(final String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
