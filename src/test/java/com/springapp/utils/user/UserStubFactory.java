package com.springapp.utils.user;

import com.springapp.jpa.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a factory class that contains stub for User fo test.
 */
public class UserStubFactory {

    private UserStubFactory() {

    }

    /**
     * This method generate a List of users
     *
     * @param numOfUser
     * @return
     */
    public static final List<User> createStubbedUserList(final int numOfUser) {
        final List<User> list = new ArrayList<>();
        for (int index = 0; index < numOfUser; index++) {
            list.add(createStubbedUser(index));
        }
        return list;
    }

    /**
     * This method generate a stubbed User object
     *
     * @param userId
     * @return
     */
    public static final User createStubbedUser(final Integer userId) {
        final User user = new User();
        user.setId(userId);
        user.setUsername("some_username");
        return user;
    }

    /**
     * This method generate a stubbed User object with custom fields.
     *
     * @param userId
     * @param username
     * @return
     */
    public static final User createStubUserWithCustomFields(final Integer userId,
                                                            final String username) {
        final User user = new User();
        user.setUsername(username);
        user.setId(userId);
        return user;
    }
}
