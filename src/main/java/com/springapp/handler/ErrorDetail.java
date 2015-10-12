package com.springapp.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class define error detail.
 */
public class ErrorDetail {

    private long timeStamp;
    private int status;
    private String title;
    private String detail;
    private String developerMessage;
    private Map<String, List<ValidationError>> errors = new HashMap<>();

    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }

    public void setErrors(final Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(final long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(final String detail) {
        this.detail = detail;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(final String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
