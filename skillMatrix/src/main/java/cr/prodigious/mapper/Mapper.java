package cr.prodigious.mapper;


import cr.prodigious.dto.cases.Entity;

import java.io.Serializable;

/**
 * Defines a Mapper
 * @author jsanca
 */
public interface Mapper<E extends Entity, B extends Serializable> {

    /**
     * Map a Bean to Entity
     * @param b B
     * @return E
     */
    E map (B b);

    /**
     * Map a Entity to Bean
     * @param e E
     * @return B
     */
    B map (E e);
} // Mapper.
