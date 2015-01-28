package cr.prodigious.dao;

import java.io.File;
import java.io.Serializable;
import java.io.Writer;

/**
 *
 * @author jsanca
 */
public class FileSystemJsonDAO extends BackupDAOHelper implements JsonDAO {


    @Override
    public boolean insertOrUpdate(String id, Serializable entity) {

        boolean success = false;
        File file = null;
        Writer writer = null;

        try {

            this.storeBackup(entity, id);
            success = true;
        } catch (Exception e) {

            success = false;
        }

        return success;
    }

    @Override
    public <T> T get (final String id, Class<T> aClass) {

        return this.getBeanFromBackupFile(id, aClass);
    }


} // E:O:F:FileSystemJsonDAO.
