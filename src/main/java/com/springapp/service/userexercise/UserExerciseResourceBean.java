package com.springapp.service.userexercise;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.SimpleUserExercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.model.UserExercise;
import com.springapp.jpa.model.UserExerciseId;
import com.springapp.jpa.repository.UserExerciseRepository;
import com.springapp.utility.mapping.UserExerciseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link com.springapp.service.userexercise.UserExerciseResource}
 */
@Service
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUserExercise(final Exercise exercise, final User user) {

        final UserExerciseId userExerciseId = new UserExerciseId();
        userExerciseId.setExercise(exercise);
        userExerciseId.setUser(user);
        userExerciseRepository.delete(userExerciseId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<SimpleUserExercise> getUsersExercises() {
        final Collection<UserExercise> result = userExerciseRepository.findAll();
        final List<SimpleUserExercise> simpleUserExercisesList = new ArrayList<>();

        for (final Iterator<UserExercise> iterator = result.iterator(); iterator.hasNext(); ) {
            simpleUserExercisesList.add(UserExerciseUtility.createSimpleUserExercise(iterator.next()));
        }

        return simpleUserExercisesList;
    }
}
