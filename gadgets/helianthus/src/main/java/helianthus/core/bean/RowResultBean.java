package helianthus.core.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 *
 * Date: 5/6/14
 * Time: 10:08 PM
 * @author jsanca
 */
public class RowResultBean implements Serializable {

    private ArrayList<Object> rows = new ArrayList<Object>();

    public void add (final Object columnValue) {

        this.rows.add(columnValue);
    }

    public ArrayList<Object> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Object> rows) {
        this.rows = rows;
    }
} // E:O:F:RowResultBean.
