package com.springapp.utils.exercise;

import com.springapp.jpa.model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates stub exercise object for tests.
 */
public class ExerciseStubFactory {

    private ExerciseStubFactory() {

    }

    /**
     * This method generate a List of exercises
     *
     * @param numberOfExercises
     * @return
     */
    public static final List<Exercise> createStubExercisesList(final int numberOfExercises) {
        final List<Exercise> exercisesList = new ArrayList<>();
        for (int index = 0; index < numberOfExercises; index++) {
            final Exercise exercise = createStubExercise(new Long(index));
            exercisesList.add(exercise);
        }
        return exercisesList;
    }

    /**
     * This method generate a stubbed Exercise object
     *
     * @param exerciseId
     * @return
     */
    public static final Exercise createStubExercise(final Long exerciseId) {
        final Exercise exercise = new Exercise();
        exercise.setDescription("some_description");
        exercise.setName("some_name");
        exercise.setId(exerciseId);
        return exercise;
    }

    /**
     * This method generate a stubbed Exercise object with custom fields.
     *
     * @param exerciseId
     * @return
     */
    public static final Exercise createStubExerciseWithCustomFields(final Long exerciseId,
                                                                    final String description,
                                                                    final String name) {
        final Exercise exercise = new Exercise();
        exercise.setDescription(description);
        exercise.setName(name);
        exercise.setId(exerciseId);
        return exercise;
    }
}
