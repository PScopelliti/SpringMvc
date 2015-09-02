package com.springapp.service.userexercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.UserExercise;
import com.springapp.jpa.model.UserExerciseId;

/**
 * The aim of this resource is to interact with UserExercise object
 */
public interface UserExerciseResource {

    /**
     * This method handle the storing of userExercise into database.
     *
     * @param userExercise
     * @return
     */
    UserExercise save(UserExercise userExercise);

    /**
     * This method return a UserExercise with specified Id.
     *
     * @param userExerciseId
     * @return
     * @throws EntityNotFoundException if user doesn't exist.
     */
    UserExercise findUserExercise(UserExerciseId userExerciseId);
}
