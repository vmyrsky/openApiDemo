package com.org.product.entity;

/**
 * Simple interface to set common id handling requirement for identifiable classes.
 */
public interface Identifiable {

    /**
     * @return The unique id of an entity.
     */
    public Long getId();
}
