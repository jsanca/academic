package helianthus.core.access;

import helianthus.core.bean.TableResultBean;

import java.io.Serializable;

/**
 * Encapsulate a methods to handle the data access
 *
 * Date: 5/6/14
 * Time: 10:04 PM
 * @author jsanca
 */
public interface GenericDataAccess extends Serializable {


    /**
     * Executes a query
     * @param query String
     * @return  TableResultBean
     */
    public TableResultBean executeQuery(String query);
} // E:O:F:GenericDataAccess.
