package com.springapp.jpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * This interface represent a base repository that contains commons methods shared between repositories.
 */
@NoRepositoryBean
public interface BaseRepository <T, ID extends Serializable> extends Repository<T, ID> {

    Collection<T> findAll();

    T save(T persisted);

    void delete(ID id);

    Optional<T> findOne(ID id);

}
