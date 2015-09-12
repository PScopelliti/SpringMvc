package com.springapp.utils.userexercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.SimpleUserExercise;
import com.springapp.jpa.model.User;
import com.springapp.jpa.model.UserExercise;
import com.springapp.jpa.model.UserExerciseId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * This is a factory class that contains stub for UserExercise for test.
 */
public class UserExerciseStubFactory {

    private UserExerciseStubFactory() {

    }

    /**
     * This method creates a stubbed UserExercise with specified User and Exercise.
     *
     * @param user
     * @param exercise
     * @return
     */
    public static final UserExercise createStubbedUserExercise(final User user,
                                                               final Exercise exercise) {
        final UserExercise userExercise = new UserExercise();
        userExercise.setUser(user);
        userExercise.setExercise(exercise);
        userExercise.setCreatedDate(new Date(100L));
        return userExercise;
    }

    /**
     * This method creates a stubbed UserExerciseID with specified User and Exercise.
     *
     * @param user
     * @param exercise
     * @return
     */
    public static final UserExerciseId createStubbedUSerExerciseId(final User user,
                                                                   final Exercise exercise) {
        final UserExerciseId userExerciseId = new UserExerciseId();
        userExerciseId.setExercise(exercise);
        userExerciseId.setUser(user);
        return userExerciseId;
    }

    /**
     * This method returns an empty collection of UserExercise.
     *
     * @param numOfElements
     * @return
     */
    public static final Collection<UserExercise> createStubbedUserExercise(final int numOfElements) {

        final Collection<UserExercise> userExercises = new ArrayList<>();

        for (int index = 0; index < numOfElements; index++) {
            final UserExercise userExercise = new UserExercise();
            userExercises.add(userExercise);
        }

        return userExercises;
    }

    /**
     * This method returns a list of SimpleUserExercises.
     * @param numOfElement
     * @return
     */
    public static final Collection<SimpleUserExercise> createStubbedSimpleUserExerciseCollection(final int numOfElement) {
        final Collection<SimpleUserExercise> simpleUserExercises = new ArrayList<>();

        for (int index = 0; index < numOfElement; index++) {
            final SimpleUserExercise simpleUserExercise = new SimpleUserExercise();
            simpleUserExercise.setUserId(index);
            simpleUserExercise.setExerciseId(index);
            simpleUserExercise.setCreationDate(new Date(100L));
            simpleUserExercises.add(simpleUserExercise);
        }

        return simpleUserExercises;
    }
}
