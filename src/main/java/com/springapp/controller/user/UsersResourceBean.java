package com.springapp.controller.user;

import com.springapp.jpa.model.User;
import com.springapp.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Implementation of {@link UsersResource}
 */
@Controller
@RequestMapping(value = "/users")
public class UsersResourceBean implements UsersResource {

    private final UserRepository userRepository;

    @Autowired
    public UsersResourceBean(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }
}
