package com.springapp.controller.user;

import com.springapp.jpa.model.User;

import java.util.List;

/**
 * The aim of this resource is to interact with users resource
 */
public interface UsersResource {

    /**
     * This method returs a list of users.
     *
     * @return
     */
    List<User> getUsers();
}
