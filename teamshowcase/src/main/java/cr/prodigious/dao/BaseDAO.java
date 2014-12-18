package cr.prodigious.dao;

import cr.prodigious.entity.Entity;

import java.io.Serializable;

/**
 * Base DAO
 * @author jsanca
 */
public abstract class BaseDAO<T extends Entity> implements Serializable {

    private DataBase dataBase = null;

    public void setDataBase(DataBaseHelper dataBase) {

        this.dataBase = dataBase;
    }

    public boolean insertOrUpdate (final T entity) {

        return this.dataBase.insert(entity);
    } // insertOrUpdate.

    public T selectById (final Long entityId) {

        return this.dataBase.selectById(entityId,
                this.getEntityClass());
    }

    protected abstract Class<T> getEntityClass ();
} // E:O:F:CasesDAO.
