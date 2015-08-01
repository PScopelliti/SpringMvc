package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
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
            method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "exerciseRegisterForm";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/register",
            method = RequestMethod.POST)
    public String processRegistration(@Valid final Exercise exercise,
                                      final Errors errors) {
        if (errors.hasErrors()) {
            return "exerciseRegisterForm";
        }

        exerciseRepository.save(exercise);
        return "redirect:/exercise/" + exercise.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public String showExerciseDetails(@PathVariable final Long id,
                                      final Model model) {

        final Optional<Exercise> result = exerciseRepository.findOne(id);

        if (result.isPresent()) {
            model.addAttribute(result.get());
        }

        return "exerciseDetails";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public void deleteExerciseById(@PathVariable
                                   final Long id) {
        exerciseRepository.delete(id);
    }
}
