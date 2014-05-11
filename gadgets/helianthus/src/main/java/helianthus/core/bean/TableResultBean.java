package helianthus.core.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a table result
 *
 * Date: 5/6/14
 * Time: 10:06 PM
 * @author jsanca
 */
public class TableResultBean implements Serializable {

    private int rowsCount = 0;


    private ColumnResultBean columnResultBean;

    private ArrayList<RowResultBean> rowResultBeans;


    public int getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public ColumnResultBean getColumnResultBean() {
        return columnResultBean;
    }

    public void setColumnResultBean(ColumnResultBean columnResultBean) {
        this.columnResultBean = columnResultBean;
    }

    public ArrayList<RowResultBean> getRowResultBeans() {
        return rowResultBeans;
    }

    public void setRowResultBeans(ArrayList<RowResultBean> rowResultBeans) {
        this.rowResultBeans = rowResultBeans;
    }
} // E:O:F:TableResultBean.
