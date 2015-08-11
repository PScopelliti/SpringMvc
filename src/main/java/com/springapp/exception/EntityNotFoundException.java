package com.springapp.exception;

import com.springapp.jpa.model.EntityId;

/**
 * This class represents an exception for handling not found entity.
 */
public class EntityNotFoundException extends RuntimeException implements EntityId {

    private final Long entityId;

    public EntityNotFoundException(final Long entityId) {
        this.entityId = entityId;
    }

    @Override
    public Long getId() {
        return entityId;
    }
}
