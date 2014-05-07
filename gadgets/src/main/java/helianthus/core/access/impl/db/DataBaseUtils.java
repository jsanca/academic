package helianthus.core.access.impl.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data base utils methods.
 *
 * Date: 5/6/14
 * Time: 10:33 PM
 * @author jsanca
 */
public class DataBaseUtils implements Serializable {

    /**
     * Close the connection quiet (without exceptions)
     * @param connection
     */
    public void closeQuiet (final Connection connection) {

        if (null != connection) {

            try {

                connection.close();
            } catch (SQLException e) {
                /** quiet */
            }
        }
    } // closeQuiet.
} // E:O:F:DataBaseUtils.
