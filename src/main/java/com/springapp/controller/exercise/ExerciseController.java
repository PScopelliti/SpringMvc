package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.service.exercise.ExerciseResource;
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

/**
 * This class define a controller for exercises.
 */
@Controller
@RequestMapping(value = "/exercises")
public class ExerciseController {

    private final ExerciseResource exerciseResource;

    @Autowired
    public ExerciseController(final ExerciseResource exerciseResource) {
        this.exerciseResource = exerciseResource;
    }

    @ResponseBody
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Exercise> processRegistration(@Valid
                                                        @RequestBody
                                                        final Exercise exercise) {
        final Exercise savedExercise = exerciseResource.save(exercise);

        final HttpHeaders headers = new HttpHeaders();
        final URI locationUri = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/exercise/")
                .path(String.valueOf(savedExercise.getId()))
                .build()
                .toUri();

        headers.setLocation(locationUri);

        final ResponseEntity<Exercise> responseEntity =
                new ResponseEntity<>(savedExercise, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Exercise showExerciseDetails(@PathVariable
                                        final Long id) {

        return exerciseResource.findExercise(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public void deleteExerciseById(@PathVariable
                                   final Long id) {

        exerciseResource.deleteExerciseById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Exercise updateExercise(@Valid
                                   @RequestBody
                                   final Exercise exercise,
                                   @PathVariable
                                   final Long id) {

        return exerciseResource.updateExercise(exercise, id);
    }

    @ResponseBody
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Exercise> getExercises() {
        return exerciseResource.getExercises();
    }
}
