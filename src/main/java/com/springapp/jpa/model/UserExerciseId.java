package com.springapp.jpa.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * This class contains the id, exercise Id and user Id.
 */
@Embeddable
public class UserExerciseId implements Serializable {

    @ManyToOne
    private User user;

    @ManyToOne
    private Exercise exercise;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(final Exercise exercise) {
        this.exercise = exercise;
    }
}
