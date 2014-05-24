package helianthus.core.access.impl.db;

import helianthus.core.bean.TableResultBean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Mapping handler to convert a ResultSet to TableResultBean
 *
 * Date: 5/6/14
 * Time: 10:53 PM
 * @author jsanca
 */
public class TableMappingHandler implements Serializable {



    /**
     * Map a ResultSet to TableResultBean
     * @param resultSet    ResultSet
     * @return   TableResultBean
     */
    public TableResultBean map (final ResultSet resultSet) throws SQLException {

        final TableResultBean tableResultBean =
                new TableResultBean();

        final ResultSetMetaData resultSetMetaData =
                resultSet.getMetaData();

        final int columnCount =
                resultSetMetaData.getColumnCount();

        final ArrayList<ArrayList<Object>> rowResultBeans =
                new ArrayList<ArrayList<Object>>() ;

        ArrayList<Object> rowResultBean = null;

        this.mapColumns
                (tableResultBean, resultSetMetaData, columnCount);

        while (resultSet.next()) {

            rowResultBean = new ArrayList<Object>();

            for (int i = 1; i <= columnCount; ++i) {

                rowResultBean.add
                        (resultSet.getObject(columnCount));
            }

            rowResultBeans.add(rowResultBean);
        }

        return tableResultBean;
    } // map.

    private void mapColumns
            (final TableResultBean tableResultBean,
             final ResultSetMetaData resultSetMetaData,
             final int columnCount) throws SQLException {

        final LinkedHashSet<String> columnNames =
                new LinkedHashSet<String>();

        for (int i = 1; i <= columnCount; ++i) {

            columnNames.add
                    (resultSetMetaData.getColumnName(i));
        }

        tableResultBean.setColumnNames(columnNames);
    } // mapColumns.
} // E:O:F:TableMappingHandler.
