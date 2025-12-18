package org.pepello.common.entity;

/**
 * Contract for domain objects that support the Prototype pattern so they can
 * produce detached copies of themselves when needed.
 */
public interface Prototype<T> {
    /**
     * Creates a new detached copy of the current entity instance.
     */
    T cloneEntity();
}
