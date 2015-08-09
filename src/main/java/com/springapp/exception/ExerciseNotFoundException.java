package com.springapp.exception;

/**
 * This class represents an exception for handling not found exercise.
 */
public class ExerciseNotFoundException extends RuntimeException {

    private final Long exerciseId;

    public ExerciseNotFoundException(final Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public long getExerciseId() {
        return exerciseId;
    }
}
