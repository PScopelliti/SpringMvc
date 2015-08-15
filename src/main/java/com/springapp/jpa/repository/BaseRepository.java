package com.springapp.jpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * This represent a base repository that contains commons methods shared between repositories.
 */
@NoRepositoryBean
public interface BaseRepository <T, ID extends Serializable> extends Repository<T, ID> {

    Collection<T> findAll();

    T save(T persisted);

    void delete(Long id);

    Optional<T> findOne(Long id);

}
