package com.springapp.jpa.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * This class contains the id, exercise Id and user Id.
 */
@Embeddable
public class UserExerciseId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserExerciseId)) {
            return false;
        }
        final UserExerciseId that = (UserExerciseId) obj;
        final EqualsBuilder eb = new EqualsBuilder();
        eb.append(user.getId(), that.user.getId());
        eb.append(exercise.getId(), that.exercise.getId());
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(user.getId());
        hcb.append(exercise.getId());
        return hcb.toHashCode();
    }
}
