package cr.prodigious.dao;

import java.io.Serializable;

/**
 * Signature for the Json DAO support
 * @author jsanca
 */
public interface JsonDAO extends Serializable {

    /**
     * Insert or update an object into a json in some data source
     * @param id        String
     * @param entity    Serializable
     * @return          boolean
     */
    public boolean insertOrUpdate (final String id, final Serializable entity);

    /**
     * Get an serializable object based on the id
     * @param id    String
     * @return      Serializable
     */
    public <T> T get (final String id, Class<T> aClass);

} // E:O:F:JsonDAO.
