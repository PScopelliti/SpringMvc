package com.springapp.controller.user;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
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
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> processRegistration(@Valid
                                                    @RequestBody
                                                    final User user) {
        final User savedUser = userRepository.save(user);

        final HttpHeaders headers = new HttpHeaders();
        final URI locationUri = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/user/")
                .path(String.valueOf(savedUser.getId()))
                .build()
                .toUri();

        headers.setLocation(locationUri);

        final ResponseEntity<User> responseEntity =
                new ResponseEntity<>(savedUser, headers, HttpStatus.CREATED);
        return responseEntity;

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
        final Optional<User> resultUser = findUser(id);
        return resultUser.get();
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
        final Optional<User> resultUser = findUser(id);
        final User us = resultUser.get();
        us.setUsername(user.getUsername());
        return userRepository.save(us);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Exercise> getExercisesPerUser(@PathVariable
                                                    final Long id) {
        final Optional<User> result = userRepository.findOne(id);
        // Check if user exists
        if (result.isPresent()) {
            return userRepository.getExercisesPerUser(id);
        }
        throw new EntityNotFoundException(id, User.class.getSimpleName());


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findUser(final Long userId) {
        final Optional<User> resultUser = userRepository.findOne(userId);

        // Check if user exists
        if (!resultUser.isPresent()) {
            throw new EntityNotFoundException(userId, User.class.getSimpleName());
        }

        return resultUser;
    }
}
