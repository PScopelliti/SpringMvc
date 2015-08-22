package com.springapp.controller.userexercise;

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
    void putExercisePerUser(Long userId, Long exerciseId);

    /**
     * This method delete an exercise for a specified user.
     *
     * @param userId
     * @param exerciseId
     */
    void deleteExercisePerUser(Long userId, Long exerciseId);

}
