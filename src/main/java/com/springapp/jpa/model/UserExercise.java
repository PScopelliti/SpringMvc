package com.springapp.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.beans.Transient;
import java.util.Date;

/**
 * This class defines a User exercise object.
 */
@Entity
@Table(name = "user_exercise", schema = "spring")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.exercise",
                joinColumns = @JoinColumn(name = "exercise_id"))})
public class UserExercise {

    @EmbeddedId
    private UserExerciseId pk = new UserExerciseId();

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date", nullable = false, length = 10)
    private Date createdDate;

    public UserExerciseId getPk() {
        return pk;
    }

    public void setPk(final UserExerciseId pk) {
        this.pk = pk;
    }

    @Transient
    @JsonIgnore
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(final User user) {
        getPk().setUser(user);
    }

    @Transient
    @JsonIgnore
    public Exercise getExercise() {
        return getPk().getExercise();
    }

    public void setExercise(final Exercise exercise) {
        getPk().setExercise(exercise);
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final UserExercise that = (UserExercise) obj;
        final EqualsBuilder eb = new EqualsBuilder();
        eb.append(pk.getUser().getId(), that.pk.getUser().getId());
        eb.append(pk.getExercise().getId(), that.pk.getExercise().getId());
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(pk.getUser().getId());
        hcb.append(pk.getExercise().getId());
        return hcb.toHashCode();
    }
}
