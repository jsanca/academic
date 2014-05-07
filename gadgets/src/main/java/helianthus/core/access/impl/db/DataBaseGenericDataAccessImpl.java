package helianthus.core.access.impl.db;

import helianthus.core.access.GenericDataAccess;
import helianthus.core.bean.TableResultBean;

import java.sql.Connection;

/**
 * DB implementation
 * Date: 5/6/14
 * Time: 10:21 PM
 * @author jsanca
 */
public class DataBaseGenericDataAccessImpl implements GenericDataAccess {

    private ConnectionProvider connectionProvider;

    /**
     * Executes a query
     *
     * @param query String
     * @return TableResultBean
     */
    @Override
    public TableResultBean executeQuery(final String query) {

        final TableResultBean tableResultBean =
                new TableResultBean();

        Connection connection = null;

        try {

        } finally {

        }


        return tableResultBean;
    } // executeQuery.
} // E:O:F:DataBaseGenericDataAccessImpl.
