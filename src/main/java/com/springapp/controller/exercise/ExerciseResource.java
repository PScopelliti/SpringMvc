package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

/**
 * The aim of this resource is to interact with a Exercise object.
 */
public interface ExerciseResource {

    /**
     * This method shows the exerciseRegistration form
     *
     * @return
     */
    String showRegistrationForm();

    /**
     * This method handle the storing of exercise into database.
     *
     * @param exercise
     * @param errors
     * @return
     */
    String processRegistration(Exercise exercise, Errors errors);

    /**
     * This method shows the exercise details.
     *
     * @param id
     * @param model
     * @return
     */
    String showExerciseDetails(Long id, Model model);
}
