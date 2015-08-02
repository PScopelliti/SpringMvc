package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;

/**
 * The aim of this resource is to interact with a Exercise object.
 */
public interface ExerciseResource {

    /**
     * This method handle the storing of exercise into database.
     *
     * @param exercise
     * @return
     */
    Exercise processRegistration(Exercise exercise);

    /**
     * This method shows the exercise details.
     *
     * @param id
     * @return
     */
    Exercise showExerciseDetails(Long id);

    /**
     * This method delete an exercise with specified id.
     *
     * @param id
     */
    void deleteExerciseById(Long id);
}
