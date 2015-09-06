package com.springapp.service.user;

import com.springapp.exception.EntityNotFoundException;
import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementation of {@link com.springapp.service.user.UserResource}
 */
@Service
public class UserResourceBean implements UserResource {

    private final UserRepository userRepository;

    @Autowired
    public UserResourceBean(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUserById(final Long id) {
        // Check if user exists.
        findUser(id);

        userRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findUser(final Long userId) {
        final Optional<User> resultUser = userRepository.findOne(userId);

        // Check if user exists
        if (!resultUser.isPresent()) {
            throw new EntityNotFoundException(userId, User.class.getSimpleName());
        }

        return resultUser.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Exercise> getExercisesPerUser(final Long id) {

        findUser(id);

        return userRepository.getExercisesPerUser(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User updateUser(final User user,
                           final Long id) {
        final User us = findUser(id);
        us.setUsername(user.getUsername());
        return userRepository.save(us);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User showUserDetails(final Long id) {
        return findUser(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(final User user) {
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }
}
