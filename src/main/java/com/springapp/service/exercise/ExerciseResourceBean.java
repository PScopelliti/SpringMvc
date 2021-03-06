package com.springapp.service.exercise;

import com.springapp.exception.ResourceNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementation of {@link com.springapp.service.exercise.ExerciseResource}
 */
@Service
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
    public Exercise findExercise(final Integer id) {
        final Optional<Exercise> result = exerciseRepository.findOne(id);

        // Check if exercise exists
        if (!result.isPresent()) {
            throw new ResourceNotFoundException("Exercise with id " + id + " not found.");
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
    public void deleteExerciseById(final Integer id) {

        findExercise(id);

        exerciseRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Exercise updateExercise(final Exercise exercise, final Integer id) {
        final Exercise ex = findExercise(id);
        ex.setDescription(exercise.getDescription());
        ex.setName(exercise.getName());
        return exerciseRepository.save(ex);
    }
}
