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
 * Class that model an exercise.
 */
@Entity
@Table(name = "exercise")
public class Exercise implements EntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id")
    private Integer id;

    @NotNull
    @Size(min = 5, max = 20)
    @Column(name = "name", length = 45, nullable = true)
    private String name;

    @NotNull
    @Size(min = 5, max = 20)
    @Column(name = "description", length = 45, nullable = true)
    private String description;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade = CascadeType.REMOVE)
    private Set<UserExercise> userExercise = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Set<UserExercise> getUserExercise() {
        return userExercise;
    }

    public void setUserExercise(final Set<UserExercise> userExercise) {
        this.userExercise = userExercise;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Exercise)) {
            return false;
        }
        final Exercise that = (Exercise) obj;
        final EqualsBuilder eb = new EqualsBuilder();
        eb.append(id, that.id);
        eb.append(name, that.name);
        eb.append(description, that.description);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(id);
        hcb.append(name);
        hcb.append(description);
        return hcb.toHashCode();
    }
}
