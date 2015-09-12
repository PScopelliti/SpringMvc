package com.springapp.service.exercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;

import java.util.Collection;

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
    Exercise save(Exercise exercise);

    /**
     * This method delete an exercise with specified id.
     *
     * @param id
     */
    void deleteExerciseById(Integer id);

    /**
     * This method update an exercise.
     *
     * @param exercise
     * @return
     */
    Exercise updateExercise(Exercise exercise, Integer id);

    /**
     * This method return an exercise with specified Id.
     *
     * @param id
     * @return
     * @throws EntityNotFoundException if user doesn't exist.
     */
    Exercise findExercise(Integer id);

    /**
     * This method returns a list of Exercises.
     *
     * @return
     */
    Collection<Exercise> getExercises();
}
