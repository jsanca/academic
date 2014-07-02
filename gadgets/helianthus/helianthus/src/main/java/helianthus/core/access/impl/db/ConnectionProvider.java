package helianthus.core.access.impl.db;

import java.io.Serializable;
import java.sql.Connection;

/**
 * Single class to provide data bases connections
 *
 * Date: 5/6/14
 * Time: 10:24 PM
 * @author jsanca
 */
public interface ConnectionProvider extends Serializable {


    /**
     * Get a connection
     * @return Connection
     */
    public Connection getConnection ();

} // E:O:F:ConnectionProvider.
