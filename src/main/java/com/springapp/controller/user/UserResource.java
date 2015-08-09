package com.springapp.controller.user;

import com.springapp.jpa.model.User;

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
    User processRegistration(User user);

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

}
