package helianthus.core.bean;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 *
 *
 * Date: 5/6/14
 * Time: 10:12 PM
 * @author jsanca
 */
public class ColumnResultBean implements Serializable {

    private LinkedHashSet<String> columnNames = new LinkedHashSet<String>();

    /**
     * Add a column
     * @param columnName String
     */
    public void add (final String columnName) {

        columnNames.add(columnName);
    } // add.

    public LinkedHashSet<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(LinkedHashSet<String> columnNames) {
        this.columnNames = columnNames;
    }
} // E:O:F:ColumnResultBean.
