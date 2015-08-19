package com.springapp.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * This class model a User
 */
@Entity
@Table(name = "user")
public class User implements EntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    @Column(name = "username", length = 45, nullable = true)
    private String username;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.exercise", cascade = CascadeType.ALL)
    private Set<UserExercise> userExercise = new HashSet<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Set<UserExercise> getUserExercise() {
        return userExercise;
    }

    public void setUserExercise(final Set<UserExercise> userExercise) {
        this.userExercise = userExercise;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
