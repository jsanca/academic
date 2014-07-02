package helianthus.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Represents a table result
 *
 * Date: 5/6/14
 * Time: 10:06 PM
 * @author jsanca
 */
public class TableResultBean implements Serializable {

    private int rowsCount = 0;

    private LinkedHashSet<String> columnNames;

    private ArrayList<ArrayList<Object>> rowResultBeans;


    public int getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }


    public LinkedHashSet<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(LinkedHashSet<String> columnNames) {
        this.columnNames = columnNames;
    }

    public ArrayList<ArrayList<Object>> getRowResultBeans() {
        return rowResultBeans;
    }

    public void setRowResultBeans(ArrayList<ArrayList<Object>> rowResultBeans) {
        this.rowResultBeans = rowResultBeans;
    }
} // E:O:F:TableResultBean.
