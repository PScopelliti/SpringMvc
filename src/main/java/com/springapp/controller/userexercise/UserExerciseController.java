package com.springapp.controller.userexercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.SimpleUserExercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.model.UserExercise;
import com.springapp.jpa.model.UserExerciseId;
import com.springapp.service.exercise.ExerciseResource;
import com.springapp.service.user.UserResource;
import com.springapp.service.userexercise.UserExerciseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Date;

/**
 * This class define a controller for userexercise.
 */
@RestController
@RequestMapping(value = "/users")
public class UserExerciseController {

    private final ExerciseResource exerciseResource;
    private final UserResource userResource;
    private final UserExerciseResource userExerciseResource;

    @Autowired
    public UserExerciseController(final UserResource userResource,
                                  final UserExerciseResource userExerciseResource,
                                  final ExerciseResource exerciseResource) {
        this.userResource = userResource;
        this.userExerciseResource = userExerciseResource;
        this.exerciseResource = exerciseResource;
    }

    /**
     * TODO:  WRITE UNIT TEST
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{userId}/exercises/{exerciseId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserExercise> postExercisePerUser(@PathVariable
                                                            final Long userId,
                                                            @PathVariable
                                                            final Long exerciseId) {

        final User resultUser = userResource.findUser(userId);

        final Exercise resultExercise = exerciseResource.findExercise(exerciseId);

        final UserExerciseId userExerciseId = new UserExerciseId();
        final UserExercise userExercise = new UserExercise();

        userExerciseId.setExercise(resultExercise);
        userExerciseId.setUser(resultUser);
        userExercise.setPk(userExerciseId);
        userExercise.setCreatedDate(new Date());

        UserExercise savedUserExercise = userExerciseResource.save(userExercise);

        final HttpHeaders headers = new HttpHeaders();
        final URI locationUri = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/users/")
                .path(String.valueOf(savedUserExercise.getUser().getId()))
                .path("/exercises/")
                .path(String.valueOf(savedUserExercise.getExercise().getId()))
                .build()
                .toUri();

        headers.setLocation(locationUri);

        final ResponseEntity<UserExercise> responseEntity =
                new ResponseEntity<>(savedUserExercise, headers, HttpStatus.CREATED);

        return responseEntity;
    }

    /**
     * TODO:  WRITE UNIT TEST
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{userId}/exercises/{exerciseId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteExercisePerUser(@PathVariable
                                      final Long userId,
                                      @PathVariable
                                      final Long exerciseId) {

        final User resultUser = userResource.findUser(userId);

        final Exercise resultExercise = exerciseResource.findExercise(exerciseId);

        userExerciseResource.deleteUserExercise(resultExercise, resultUser);
    }

    /**
     * {@inheritDoc}
     * TODO:  WRITE UNIT TEST
     */
    @RequestMapping(value = "/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SimpleUserExercise> getUserExercises() {
        return userExerciseResource.getUsersExercises();
    }
}
