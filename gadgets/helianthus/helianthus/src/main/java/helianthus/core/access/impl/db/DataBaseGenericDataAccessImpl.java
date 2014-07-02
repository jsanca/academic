package helianthus.core.access.impl.db;

import helianthus.core.DataAccessErrorException;
import helianthus.core.IncongruentColumnValueLengthException;
import helianthus.core.access.GenericDataAccess;
import helianthus.core.bean.TableResultBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
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

    private ConnectionProviderService connectionProviderService;

    private DataBaseUtils dataBaseUtils =
            new DataBaseUtils();

    private TableMappingHandler tableMappingHandler =
            new TableMappingHandler();

    /**
     * Executes a query
     *
     * @param query String
     * @return TableResultBean
     */
    @Override
    public TableResultBean executeQuery(final String query,
                                        final String [] typeNameArray,
                                        String dataSource,
                                        final Object... params) {

        TableResultBean tableResultBean = null;
        Connection connection       = null;
        PreparedStatement statement = null;
        ResultSet resultSet         = null;
        ConnectionProvider connectionProvider = null;

        try {

            if (null == dataSource) {

                dataSource = DEFAULT_DATA_SOURCE;
            }

            connectionProvider =
                    this.connectionProviderService.getConnectionProvider
                            (dataSource);

            connection =
                    connectionProvider.getConnection();

            statement =
                    connection.prepareStatement
                        (query);

            this.setParams(statement, typeNameArray, params);

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
            final String [] typeNameArray,
            final Object[] params) throws SQLException {

        int paramIndex = 1;

        if (null != params) {

            if (typeNameArray.length == params.length) {

                for (Object param : params) {

                    statement.setObject(paramIndex++, param);
                }
            } else {

                throw new IncongruentColumnValueLengthException
                        (MessageFormat.format(
                                "You are sending {0} values, but the query is expecting {1}",
                                params.length, typeNameArray.length));
            }
        }
    } // setParams.


    public void setConnectionProviderService(ConnectionProviderService connectionProviderService) {
        this.connectionProviderService = connectionProviderService;
    }


} // E:O:F:DataBaseGenericDataAccessImpl.
