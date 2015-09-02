package com.springapp.service.user;

import com.springapp.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of {@link com.springapp.service.user.UserResource}
 */
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
}
