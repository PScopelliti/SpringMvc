package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Implementation of {@link ExercisesResource}
 */
@Controller
@RequestMapping(value = "/exercises")
public class ExercisesResourceBean implements ExercisesResource {


    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExercisesResourceBean(final ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

}