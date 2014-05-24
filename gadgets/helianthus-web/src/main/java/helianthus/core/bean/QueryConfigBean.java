package helianthus.core.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a query config bean
 *
 * Date: 5/20/14
 * Time: 6:44 PM
 * @author jsanca
 */
public class QueryConfigBean implements Serializable {

    private String name;
    private String datasource;
    private String query;

    private ArrayList<QueryParameterBean> parameters;


    public ArrayList<QueryParameterBean> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<QueryParameterBean> parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
} // E:O:F:QueryConfigBean.
