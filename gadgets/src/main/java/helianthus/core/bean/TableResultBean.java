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


} // E:O:F:TableResultBean.
