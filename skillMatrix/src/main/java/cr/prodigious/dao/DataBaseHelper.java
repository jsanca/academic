package cr.prodigious.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import cr.prodigious.bean.team.TeamBean;
import cr.prodigious.entity.Entity;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Helper to perform the database
 *
 * @author jsanca
 */
public class DataBaseHelper implements Serializable, DataBase, cr.prodigious.bean.InitDelegate {

    private ObjectContainer dataBase = null;
    private String dataBasePath;
    private String backupDataBasePatternPath = "backup{0}.db4o";

    public ObjectContainer getDataBase() {

        return dataBase;
    }

    @Override
    public synchronized void init() {

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        this.dataBase = Db4oEmbedded.openFile(config,
                this.getDataBasePath());

        config.common().objectClass(TeamBean.class).cascadeOnActivate(
                true);
    } // init.

    @Override
    public synchronized boolean insert(final Entity entity) {

        boolean result = false;

        try {

            this.dataBase.store(entity);
            this.dataBase.ext().commit();

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

                        return id.equals(Entity.class.cast(candidate).getUserId());
                    }
                });

        if ((null != entityList) &&
                (entityList.size() >= 1)) {

            entity = entityList.get(0);
        }

        return entity;
    } // selectById.

    @Override
    public <T extends Entity> List<T> selectByPredicate
            (final cr.prodigious.dao.Predicate<T> predicate) {

        List<T> entityList = null;

        entityList =
                this.selectByPredicate(new com.db4o.query.Predicate<T>() {

                    @Override
                    public boolean match(final T candidate) {

                        return predicate.match(candidate);
                    }
                });

        return entityList;
    } // selectByPredicate.

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

    @Override
    public Entity activate(Entity entity, int depth) {

        this.dataBase.activate(entity, depth);

        return entity;
    }

    @Override
    public boolean backupMe() {

        boolean backup = false;

        try {

            this.dataBase.ext().backup
                    (MessageFormat.format(this.getBackupDataBasePatternPath(),
                            String.valueOf(new Date().getTime())));

            backup = true;
        } catch (Exception e) {

            backup = false;
        }

        return backup;
    }

    @Override
    public boolean closeMe() {

        boolean closed = false;

        // TODO: backup me?
        if (null != this.dataBase) {

            if (!this.dataBase.ext().isClosed()) {

                closed =
                    this.dataBase.close();
            }
        }

        return closed;
    }

    public String getDataBasePath() {

        return this.dataBasePath;
    }

    public void setDataBasePath(String dataBasePath) {

        this.dataBasePath = dataBasePath;
    }

    public String getBackupDataBasePatternPath() {
        return backupDataBasePatternPath;
    }

    public void setBackupDataBasePatternPath(String backupDataBasePatternPath) {
        this.backupDataBasePatternPath = backupDataBasePatternPath;
    }

    @Override
    public synchronized void close() {

        if (null != this.dataBase) {

            if (!this.dataBase.ext().isClosed()) {

                this.dataBase.close();
            }
        }
    }
} // E:O:F:DataBaseHelper.
