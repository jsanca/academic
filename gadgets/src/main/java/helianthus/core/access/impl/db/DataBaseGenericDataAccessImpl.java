package helianthus.core.access.impl.db;

import helianthus.core.DataAccessErrorException;
import helianthus.core.access.GenericDataAccess;
import helianthus.core.bean.TableResultBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DB implementation
 * Date: 5/6/14
 * Time: 10:21 PM
 * @author jsanca
 */
public class DataBaseGenericDataAccessImpl implements GenericDataAccess {

    private static Logger logger =
            Logger.getLogger(DataBaseGenericDataAccessImpl.class.getName());

    private ConnectionProvider connectionProvider;

    private DataBaseUtils dataBaseUtils;

    private TableMappingHandler tableMappingHandler;

    /**
     * Executes a query
     *
     * @param query String
     * @return TableResultBean
     */
    @Override
    public TableResultBean executeQuery(final String query, final Object... params) {

        TableResultBean tableResultBean = null;
        Connection connection       = null;
        PreparedStatement statement = null;
        ResultSet resultSet         = null;

        try {

            connection =
                    this.connectionProvider.getConnection();

            statement =
                    connection.prepareStatement
                        (query);

            this.setParams(statement, params);

            resultSet =
                    statement.executeQuery();

            tableResultBean =
                    tableMappingHandler.map(resultSet);

        } catch (SQLException e) {

            logger.log(Level.INFO, e.getMessage(), e);
            throw new DataAccessErrorException(e);
        } finally {

             dataBaseUtils.closeQuiet(resultSet, statement, connection);
        }


        return tableResultBean;
    } // executeQuery.

    private void setParams(
            final PreparedStatement statement,
            final Object[] params) throws SQLException {

        int paramIndex = 1;

        if (null != params) {

            for (Object param : params) {

                statement.setObject(paramIndex++, param);
            }
        }
    } // setParams.


    public void setConnectionProvider(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void setDataBaseUtils(DataBaseUtils dataBaseUtils) {
        this.dataBaseUtils = dataBaseUtils;
    }
} // E:O:F:DataBaseGenericDataAccessImpl.
