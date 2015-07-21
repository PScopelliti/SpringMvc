package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Implementation of {@link com.springapp.controller.exercise.ExerciseResource}
 */
@Controller
@RequestMapping(value = "/exercise")
public class ExerciseResourceBean implements ExerciseResource {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseResourceBean(final ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "exerciseRegisterForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(
            @Valid Exercise exercise,
            Errors errors) {
        if (errors.hasErrors()) {
            return "exerciseRegisterForm";
        }

        exerciseRepository.save(exercise);
        return "redirect:/spitter/" + spitter.getUsername();
    }

}
