package com.springapp.repository;

import com.springapp.model.Exercise;

import java.util.List;

/**
 * A repository class for esercises.
 */
public interface ExerciseRepository {

    public List<Exercise> findExerciseById(int exerciseId);
}
