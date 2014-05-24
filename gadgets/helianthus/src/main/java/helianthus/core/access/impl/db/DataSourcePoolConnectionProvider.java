package helianthus.core.access.impl.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data source implementation
 * Date: 5/22/14
 * Time: 10:12 PM
 * @author jsanca
 */
public class DataSourcePoolConnectionProvider implements ConnectionProvider {

    private javax.sql.DataSource dataSource;

    /**
     * Get a connection
     *
     * @return Connection
     */
    @Override
    public Connection getConnection() {

        Connection connection = null;

        try {

            connection =  this.dataSource.getConnection();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return connection;
    } // getConnection.

    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }
} // E:O:F:DataSourcePoolConnectionProvider.
