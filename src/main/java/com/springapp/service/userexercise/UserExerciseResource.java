package com.springapp.service.userexercise;

import com.springapp.exception.ResourceNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.SimpleUserExercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.model.UserExercise;
import com.springapp.jpa.model.UserExerciseId;

import java.util.Collection;

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
     * @throws ResourceNotFoundException if user doesn't exist.
     */
    UserExercise findUserExercise(UserExerciseId userExerciseId);

    /**
     * This method remove a UserExercise with specified id.
     *
     * @param exercise
     * @param user
     */
    void deleteUserExercise(Exercise exercise, User user);

    /**
     * This method returns a list of userExercises.
     *
     * @return
     */
    Collection<SimpleUserExercise> getUsersExercises();
}
