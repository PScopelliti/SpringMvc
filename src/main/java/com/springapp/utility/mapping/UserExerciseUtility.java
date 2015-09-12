package com.springapp.utility.mapping;

import com.springapp.jpa.model.SimpleUserExercise;
import com.springapp.jpa.model.UserExercise;

/**
 * This is a utility class for UserExercise.
 */

public class UserExerciseUtility {

    private UserExerciseUtility() {

    }

    /**
     * This method creates a SimpleUserExercise from UserExercise object.
     *
     * @param userExercise
     * @return
     */
    public static final SimpleUserExercise createSimpleUserExercise(final UserExercise userExercise) {
        final SimpleUserExercise simpleUserExercise = new SimpleUserExercise();
        simpleUserExercise.setUserId(userExercise.getUser() != null ? userExercise.getUser().getId() : null);
        simpleUserExercise.setExerciseId(userExercise.getExercise() != null ? userExercise.getExercise().getId() : null);
        simpleUserExercise.setCreationDate(userExercise.getCreatedDate());
        return simpleUserExercise;
    }
}
