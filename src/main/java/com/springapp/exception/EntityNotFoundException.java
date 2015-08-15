package com.springapp.exception;

import com.springapp.jpa.model.EntityId;

/**
 * This class represents an exception for handling not found entity.
 */
public class EntityNotFoundException extends RuntimeException implements EntityId {

    private final Long entityId;
    private final String entityName;

    public EntityNotFoundException(final Long entityId,
                                   final String entityName) {
        this.entityId = entityId;
        this.entityName = entityName;
    }

    @Override
    public Long getId() {
        return entityId;
    }

    public String getEntityName() {
        return entityName;
    }
}
