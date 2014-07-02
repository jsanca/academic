package helianthus.core.bean;

import java.io.Serializable;

/**
 * Represents a query parameter for the query configuration
 *
 * Date: 5/20/14
 * Time: 6:47 PM
 * @author jsanca
 */
public class QueryParameterBean  implements Serializable {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
} // E:O:F:QueryParameterBean.
