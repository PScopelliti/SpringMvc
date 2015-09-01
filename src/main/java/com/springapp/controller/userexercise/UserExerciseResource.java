package com.springapp.controller.userexercise;

import com.springapp.jpa.model.UserExercise;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

/**
 * The aim of this resource is to interact with UserExercise resource
 */
public interface UserExerciseResource {

    /**
     * This method assign an exercise to a specified user.
     *
     * @param userId
     * @param exerciseId
     */
    void putExercisePerUser(Long userId, Long exerciseId, UserExercise userExercise);

    /**
     * This method delete an exercise for a specified user.
     *
     * @param userId
     * @param exerciseId
     */
    void deleteExercisePerUser(Long userId, Long exerciseId);

    /**
     * This method returns a list of UserExercise.
     * Returns the full object with User and Exercise,
     * probably this could be cause performance issues.
     *
     * @return
     */
    Collection<UserExercise> getUserExercises();

    /**
     * @return
     */
    ResponseEntity<UserExercise> postExercisePerUser(Long userId, Long exerciseId);

}
