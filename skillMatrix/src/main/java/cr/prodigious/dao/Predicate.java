package cr.prodigious.dao;

import cr.prodigious.entity.Entity;

import java.io.Serializable;

/**
 * A predicate is helpful to filter results
 *
 * @author jsanca
 */
public interface Predicate<T extends Entity> extends Serializable {

    /**
     * Determine if the entity match with the Predicate criteria
     * @param entity   T
     * @return boolean
     */
    public abstract boolean match(T entity);
} // E:O:F:Predicate.
