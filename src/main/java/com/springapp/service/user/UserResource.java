package com.springapp.service.user;

import com.springapp.exception.ResourceNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;

import java.util.Collection;

/**
 * The aim of this resource is to interact with user object
 */
public interface UserResource {

    /**
     * This method remove user by id.
     *
     * @param id
     */
    void deleteUserById(Integer id);


    /**
     * This method handle the storing of users into database.
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     * This method shows user details.
     *
     * @param id
     * @return
     */
    User showUserDetails(Integer id);

    /**
     * This method update an user.
     *
     * @param user
     * @return
     */
    User updateUser(User user, Integer id);

    /**
     * This method return a list of exercises for a specified user
     *
     * @param id
     * @return
     */
    Collection<Exercise> getExercisesPerUser(Integer id);

    /**
     * This method return a user with specified Id.
     *
     * @param userId
     * @return
     * @throws ResourceNotFoundException if user doesn't exist.
     */
    User findUser(Integer userId);

    /**
     * This method returns a list of users.
     *
     * @return
     */
    Collection<User> getUsers();
}

