package com.springapp.service.exercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementation of {@link com.springapp.service.exercise.ExerciseResource}
 */
@Controller
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
    public Exercise findExercise(final Long id) {
        final Optional<Exercise> result = exerciseRepository.findOne(id);

        // Check if exercise exists
        if (!result.isPresent()) {
            throw new EntityNotFoundException(id, Exercise.class.getSimpleName());
        }

        return result.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    /**
     * This method handle the storing of exercise into database.
     *
     * @param exercise
     * @return
     */
    @Override
    public Exercise save(final Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteExerciseById(final Long id) {

        findExercise(id);

        exerciseRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Exercise updateExercise(final Exercise exercise, final Long id) {
        final Exercise ex = findExercise(id);
        ex.setDescription(exercise.getDescription());
        ex.setName(exercise.getName());
        return exerciseRepository.save(ex);
    }
}
