package com.springapp.controller.exercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

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
    ResponseEntity<Exercise> processRegistration(Exercise exercise);

    /**
     * This method shows exercise details.
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

    /**
     * This method update an exercise.
     *
     * @param exercise
     * @return
     */
    Exercise updateExercise(Exercise exercise, Long id);

    /**
     * This method return an exercise with specified Id.
     *
     * @param id
     * @return
     * @throws EntityNotFoundException if user doesn't exist.
     */
    Optional<Exercise> findExercise(Long id);
}
