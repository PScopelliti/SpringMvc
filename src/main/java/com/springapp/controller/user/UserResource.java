package com.springapp.controller.user;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

/**
 * The aim of this resource is to interact with user resource
 */
public interface UserResource {

    /**
     * This method remove user by id.
     *
     * @param id
     */
    void deleteUserById(Long id);


    /**
     * This method handle the storing of users into database.
     *
     * @param user
     * @return
     */
    ResponseEntity<User> processRegistration(User user);

    /**
     * This method shows user details.
     *
     * @param id
     * @return
     */
    User showUserDetails(Long id);

    /**
     * This method update an user.
     *
     * @param user
     * @return
     */
    User updateUser(User user, Long id);

    /**
     * This method return a list of exercises for a specified user
     *
     * @param id
     * @return
     */
    Collection<Exercise> getExercisesPerUser(Long id);

    /**
     * This method assign an exercise to a specified user.
     *
     * @param userId
     * @param exerciseId
     */
    void setExercisePerUser(Long userId, Long exerciseId);

}
