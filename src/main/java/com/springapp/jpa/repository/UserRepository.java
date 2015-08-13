package com.springapp.jpa.repository;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * This class represent a user repository.
 */
public interface UserRepository extends BaseRepository<User, Long> {

    String GET_EXERCISES_PER_USER_QUERY = "SELECT e " +
            "FROM Exercise e LEFT JOIN e.users u " +
            "WHERE u.id = :userId";

    String UPDATE_USER_EXERCISE = "UPDATE Exercise e " +
            "SET e.users.user_id = :userId, e.users.exercise_id = :exerciseId";

    /**
     * Get exercises per User
     */
    @Query(GET_EXERCISES_PER_USER_QUERY)
    Collection<Exercise> getExercisesPerUser(@Param("userId") Long userId);

}
