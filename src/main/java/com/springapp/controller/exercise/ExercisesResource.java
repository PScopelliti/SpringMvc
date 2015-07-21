package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;

import java.util.List;

/**
 * The aim of this resource is to interact with a collection of Exercise object.
 */
public interface ExercisesResource {

    /**
     * This method returns a list of Exercises.
     *
     * @return
     */
    List<Exercise> getExercises();

}