package com.springapp.jpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * This represent a base repository that contains commons methods shared between repositories.
 */
@NoRepositoryBean
public interface BaseRepository <T, ID extends Serializable> extends Repository<T, ID> {

    List<T> findAll();

    T save(T persisted);

    void delete(T deleted);

    Optional<T> findOne(Long id);

}
