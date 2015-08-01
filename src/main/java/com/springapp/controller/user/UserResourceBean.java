package com.springapp.controller.user;

import com.springapp.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Implementation of {@link UserResource}
 */
@Controller
@RequestMapping(value = "/user")
public class UserResourceBean implements UserResource {

    private final UserRepository userRepository;

    @Autowired
    public UserResourceBean(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public void deleteUserById(@PathVariable
                               final Long id) {
        userRepository.delete(id);
    }

}
