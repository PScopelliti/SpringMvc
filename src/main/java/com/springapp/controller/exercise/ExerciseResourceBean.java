package com.springapp.controller.exercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
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
import java.util.Optional;

/**
 * Implementation of {@link com.springapp.controller.exercise.ExerciseResource}
 */
@Controller
@RequestMapping(value = "/exercise")
public class ExerciseResourceBean implements ExerciseResource {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseResourceBean(final ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
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
    public ResponseEntity<Exercise> processRegistration(@Valid
                                                        @RequestBody
                                                        final Exercise exercise) {
        final Exercise savedExercise = exerciseRepository.save(exercise);

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

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Exercise showExerciseDetails(@PathVariable
                                        final Long id) {

        final Optional<Exercise> result = exerciseRepository.findOne(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new EntityNotFoundException(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteExerciseById(@PathVariable
                                   final Long id) {
        final Optional<Exercise> result = exerciseRepository.findOne(id);

        if (!result.isPresent()) {
            throw new EntityNotFoundException(id);
        }

        exerciseRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Exercise updateExercise(@Valid
                                   @RequestBody
                                   final Exercise exercise,
                                   @PathVariable
                                   final Long id) {
        final Optional<Exercise> result = exerciseRepository.findOne(id);
        if (result.isPresent()) {
            final Exercise ex = result.get();
            ex.setDescription(exercise.getDescription());
            ex.setName(exercise.getName());
            return exerciseRepository.save(ex);
        }
        throw new EntityNotFoundException(id);
    }
}
