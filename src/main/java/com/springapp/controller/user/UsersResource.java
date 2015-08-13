package com.springapp.controller.user;

import com.springapp.jpa.model.User;

import java.util.Collection;

/**
 * The aim of this resource is to interact with users resource
 */
public interface UsersResource {

    /**
     * This method returns a list of users.
     *
     * @return
     */
    Collection<User> getUsers();

}
