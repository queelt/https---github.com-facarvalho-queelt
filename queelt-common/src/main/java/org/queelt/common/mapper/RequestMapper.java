package org.queelt.common.mapper;

/**
 * The interface Request mapper.
 */
public interface RequestMapper<O, I> {

    /**
     * Map.
     *
     * @param i the i
     * @return the o
     */
    O map(I i);
}