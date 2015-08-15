package com.springapp.controller.user;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.repository.ExerciseRepository;
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
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public UserResourceBean(final UserRepository userRepository,
                            final ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
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

        final Optional<User> result = userRepository.findOne(id);

        if (!result.isPresent()) {
            throw new EntityNotFoundException(id, User.class.getSimpleName());
        }

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
        final Optional<User> result = userRepository.findOne(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new EntityNotFoundException(id, User.class.getSimpleName());
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
        throw new EntityNotFoundException(id, User.class.getSimpleName());
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
     * TODO:  WRITE UNIT TEST
     */
    @Override
    @RequestMapping(value = "/{userId}/exercise/{exerciseId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void setExercisePerUser(@PathVariable
                                   final Long userId,
                                   @PathVariable
                                   final Long exerciseId) {

        final Optional<User> resultUser = userRepository.findOne(userId);
        // Check if user exists
        if (!resultUser.isPresent()) {
            throw new EntityNotFoundException(userId, User.class.getSimpleName());
        }

        final Optional<Exercise> resultExercise = exerciseRepository.findOne(exerciseId);
        // Check if exercise exists
        if (!resultExercise.isPresent()) {
            throw new EntityNotFoundException(userId, Exercise.class.getSimpleName());
        }


    }

}
