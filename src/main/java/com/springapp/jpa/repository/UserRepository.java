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

    String GET_EXERCISES_PER_USER_QUERY = "SELECT u.pk.exercise " +
            "FROM Exercise e LEFT JOIN e.userExercise u " +
            "WHERE u.pk.user.id = :userId";
//
//    String UPDATE_EXERCISE_PER_USER_QUERY = "UPDATE Exercise e " +
//            "SET e.users.id = :userId, e.users.exercise_id = :exerciseId";

    /**
     * Get exercises per User
     */
    @Query(GET_EXERCISES_PER_USER_QUERY)
    Collection<Exercise> getExercisesPerUser(@Param("userId")
                                             Long userId);

//    @Query(UPDATE_EXERCISE_PER_USER_QUERY)
//    void updateExercisePerUser(@Param("userId")
//                               Long userId,
//                               @Param("exerciseId")
//                               Long exerciseId);
}
