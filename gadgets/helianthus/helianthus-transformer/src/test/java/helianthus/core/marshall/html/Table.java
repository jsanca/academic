package helianthus.core.marshall.html;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boszdigital1
 * Date: 5/17/14
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Table {

    private ArrayList<Column> columns = null;

    private ArrayList<ArrayList<Data>> data = null;

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public ArrayList<ArrayList<Data>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<Data>> data) {
        this.data = data;
    }
}
