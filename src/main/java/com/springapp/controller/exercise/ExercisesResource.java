package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;

import java.util.Collection;

/**
 * The aim of this resource is to interact with a collection of Exercise object.
 */
public interface ExercisesResource {

    /**
     * This method returns a list of Exercises.
     *
     * @return
     */
    Collection<Exercise> getExercises();

}