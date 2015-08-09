package com.springapp.controller.user;

import com.springapp.jpa.model.User;
import com.springapp.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.Optional;

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
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable
                               final Long id) {
        userRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User processRegistration(@Valid
                                    @RequestBody
                                    final User user) {
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User showUserDetails(@PathVariable
                                final Long id) {
        final Optional<User> result = userRepository.findOne(id);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User updateUser(@Valid
                           @RequestBody
                           final User user,
                           @PathVariable
                           final Long id) {
        final Optional<User> result = userRepository.findOne(id);
        if (result.isPresent()) {
            final User us = result.get();
            us.setUsername(user.getUsername());
            return userRepository.save(us);
        }
        return null;
    }

}
