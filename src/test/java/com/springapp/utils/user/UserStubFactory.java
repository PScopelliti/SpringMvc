package com.springapp.utils.user;

import com.springapp.jpa.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a factory class that contains stub for User fo tetst.
 */
public class UserStubFactory {

    private UserStubFactory() {

    }

    public static final List<User> createStubbedUserList(final int numOfUser) {
        final List<User> list = new ArrayList<>();
        for (int index = 0; index < numOfUser; index++) {
            list.add(createStubbedUser(index));
        }
        return list;
    }

    public static final User createStubbedUser(final int userId) {
        final User user = new User();
        user.setId(userId);
        user.setUsername("some_username");
        return user;
    }
}
