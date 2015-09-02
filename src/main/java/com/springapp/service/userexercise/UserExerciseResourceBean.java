package com.springapp.service.userexercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.UserExercise;
import com.springapp.jpa.model.UserExerciseId;
import com.springapp.jpa.repository.UserExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Implementation of {@link com.springapp.service.userexercise.UserExerciseResource}
 */
public class UserExerciseResourceBean implements UserExerciseResource {

    private final UserExerciseRepository userExerciseRepository;

    @Autowired
    public UserExerciseResourceBean(final UserExerciseRepository userExerciseRepository) {
        this.userExerciseRepository = userExerciseRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserExercise save(final UserExercise userExercise) {
        return userExerciseRepository.save(userExercise);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserExercise findUserExercise(final UserExerciseId userExerciseId) {
        final Optional<UserExercise> resultUserExercise = userExerciseRepository.findOne(userExerciseId);

        // Check if userExercise exists
        if (!resultUserExercise.isPresent()) {
            throw new EntityNotFoundException(null, UserExercise.class.getSimpleName());
        }

        return resultUserExercise.get();
    }
}
