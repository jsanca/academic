package cr.prodigious.dao;

import cr.prodigious.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the interface for the DataBase operations
 * @author jsanca
 */
public interface DataBase extends Serializable {
    boolean insert(Entity entity) // insert
    ;

    boolean update(Entity entity) // update
    ;

    boolean delete(Entity entityToDelete) // delete
    ;

    <T extends Entity> T selectById(Long id, Class<T> entityClass) // selectById.
    ;

    List<Entity> selectAll(Class<Entity> entityClass) // selectAll.
    ;

    public Entity activate (final Entity entity, final int depth);

    boolean backupMe ();

    boolean closeMe ();
} // E:O:F:DataBase.
