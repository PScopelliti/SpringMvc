package com.springapp.jpa.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is a simplified version of UserExercise.
 * It contains only the relevant fields.
 * It is useful object response.
 */
public class SimpleUserExercise implements Serializable {

    private static final long serialVersionUID = 34955927763320848L;
    private Long userId;
    private Long exerciseId;
    private Date creationDate;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(final Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

}
