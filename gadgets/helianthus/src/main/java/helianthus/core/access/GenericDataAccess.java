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

    public static final String DEFAULT_DATA_SOURCE = "default";

    /**
     * Executes a query
     *
     * @param query String
     * @param typeNameArray String array
     * @param dataSource String if it is null will look for the default
     * @param params Object array
     *
     * @return  TableResultBean
     */
    public TableResultBean executeQuery(String query,
                                        String [] typeNameArray,
                                        String dataSource,
                                        Object... params);

} // E:O:F:GenericDataAccess.
