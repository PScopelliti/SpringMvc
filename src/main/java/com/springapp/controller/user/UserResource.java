package com.springapp.controller.user;

/**
 * The aim of this resource is to interact with user resource
 */
public interface UserResource {

    /**
     * This method remove userby id.
     *
     * @param id
     */
    void deleteUserById(Long id);

}
