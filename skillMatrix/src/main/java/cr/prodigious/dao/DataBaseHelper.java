package cr.prodigious.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import cr.prodigious.entity.Entity;

import javax.sql.rowset.Predicate;
import java.io.Serializable;
import java.util.List;

/**
 * Helper to perform the database
 *
 * @author jsanca
 */
public class DataBaseHelper implements Serializable, DataBase, cr.prodigious.bean.InitDelegate {

    private ObjectContainer dataBase = null;
    private String dataBasePath;

    public ObjectContainer getDataBase() {
        return dataBase;
    }

    @Override
    public synchronized void init() {

        this.dataBase = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                this.getDataBasePath());
    } // init.

    @Override
    public synchronized boolean insert(final Entity entity) {

        boolean result = false;

        try {

            this.dataBase.store(entity);

            this.dataBase.commit();
            result = true;
        } catch (Exception e) {

            this.dataBase.rollback();
            result = false;
        }

        return result;
    } // insert

    @Override
    public boolean update(final Entity entity) {

        return this.insert(entity);
    } // update

    @Override
    public boolean delete(final Entity entityToDelete) {

        boolean result = false;

        try {

            this.dataBase.delete(entityToDelete);

            this.dataBase.commit();
            result = true;
        } catch (Exception e) {

            this.dataBase.rollback();
            result = false;
        }

        return result;
    } // delete

    @Override
    public <T extends Entity> T selectById(final Long id, final Class<T> entityClass) {

        T entity = null;
        List<T> entityList = null;

        entityList =
                this.selectByPredicate(new com.db4o.query.Predicate<T>() {

                    @Override
                    public boolean match(final T candidate) {

                        return id.equals(Entity.class.cast(candidate).getId());
                    }
                });

        if ((null != entityList) &&
                (entityList.size() >= 1)) {

            entity = entityList.get(0);
        }

        return entity;
    } // selectById.

    public <T extends Entity> List<T> selectByPredicate
            (final com.db4o.query.Predicate<T> predicate) {

        List<T> entityList = null;

        entityList =
                this.dataBase.query(predicate);

        return entityList;
    } // selectByPredicate.

    @Override
    public List<Entity> selectAll(final Class<Entity> entityClass) {

        return this.dataBase.queryByExample(entityClass);
    } // selectAll.

    public String getDataBasePath() {

        return this.dataBasePath;
    }

    public void setDataBasePath(String dataBasePath) {

        this.dataBasePath = dataBasePath;
    }

    @Override
    public synchronized void close() {

        if (null != this.dataBase) {

            this.dataBase.close();
        }
    }
} // E:O:F:DataBaseHelper.
