package helianthus.core.bean;

import java.io.Serializable;

/**
 *
 *
 * Date: 5/11/14
 * Time: 11:50 AM
 * @author jsanca
 */
public class QueryMappingResult implements Serializable {

    private String dataSource = null;
    private String query = null;
    private String [] typeNameArray = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String[] getTypeNameArray() {
        return typeNameArray;
    }

    public void setTypeNameArray(String[] typeNameArray) {
        this.typeNameArray = typeNameArray;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
} // E:O:F:QueryMappingResult.
