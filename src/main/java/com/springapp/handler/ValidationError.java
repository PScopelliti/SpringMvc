package com.springapp.handler;

/**
 * This class contains the error message defined for the field that are in errors.
 */
public class ValidationError {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
