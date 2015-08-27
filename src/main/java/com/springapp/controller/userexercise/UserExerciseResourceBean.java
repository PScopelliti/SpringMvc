package com.springapp.controller.userexercise;

import com.springapp.controller.exercise.ExerciseResource;
import com.springapp.controller.user.UserResource;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.model.UserExercise;
import com.springapp.jpa.model.UserExerciseId;
import com.springapp.jpa.repository.UserExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * Implementation of {@link UserExerciseResource}
 */
@Controller
@RequestMapping(value = "/user")
public class UserExerciseResourceBean implements UserExerciseResource {

    private UserResource userResource;
    private ExerciseResource exerciseResource;
    private UserExerciseRepository userExerciseRepository;

    @Autowired
    public UserExerciseResourceBean(final UserResource userResource,
                                    final ExerciseResource exerciseResource,
                                    final UserExerciseRepository userExerciseRepository) {
        this.userResource = userResource;
        this.exerciseResource = exerciseResource;
        this.userExerciseRepository = userExerciseRepository;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putExercisePerUser(@PathVariable
                                   final Long userId,
                                   @PathVariable
                                   final Long exerciseId) {

        final Optional<User> resultUser = userResource.findUser(userId);

        final Optional<Exercise> resultExercise = exerciseResource.findExercise(exerciseId);

        final UserExerciseId userExerciseId = new UserExerciseId();
        final UserExercise userExercise = new UserExercise();

        userExerciseId.setExercise(resultExercise.get());
        userExerciseId.setUser(resultUser.get());
        userExercise.setPk(userExerciseId);
        userExercise.setCreatedDate(new Date());

        userExerciseRepository.save(userExercise);
    }

    /**
     * {@inheritDoc}
     * TODO:  WRITE UNIT TEST
     */
    @Override
    @RequestMapping(value = "/{userId}/exercise/{exerciseId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercisePerUser(@PathVariable
                                      final Long userId,
                                      @PathVariable
                                      final Long exerciseId) {
        final Optional<User> resultUser = userResource.findUser(userId);

        final Optional<Exercise> resultExercise = exerciseResource.findExercise(exerciseId);

        final UserExerciseId userExerciseId = new UserExerciseId();
        userExerciseId.setExercise(resultExercise.get());
        userExerciseId.setUser(resultUser.get());

        userExerciseRepository.delete(userExerciseId);
    }

    /**
     * {@inheritDoc}
     * TODO:  WRITE UNIT TEST
     */
    @Override
    @RequestMapping(value = "/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<UserExercise> getUserExercises() {
        return userExerciseRepository.findAll();
    }
}
