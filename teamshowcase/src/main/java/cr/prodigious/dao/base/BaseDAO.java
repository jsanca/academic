package cr.prodigious.dao.base;

import cr.prodigious.dao.DataBase;
import cr.prodigious.dao.DataBaseHelper;
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

    public DataBase getDataBase() {

        return dataBase;
    }

    public boolean insertOrUpdate (final T entity) {

        return this.dataBase.insert(entity);
    } // insertOrUpdate.

    public T selectById (final Long entityId) {

         T t = this.dataBase.selectById(entityId,
                this.getEntityClass());

        if (null != t) {

            // you need to activate the object in order to
            // get fill out the composite objects
            this.dataBase.activate(t, getDepth());
        }

        return t;
    }

    protected abstract int getDepth ();
    protected abstract Class<T> getEntityClass ();
} // E:O:F:CasesDAO.
