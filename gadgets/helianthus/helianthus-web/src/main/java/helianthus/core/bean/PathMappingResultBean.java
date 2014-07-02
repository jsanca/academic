package helianthus.core.bean;

import java.io.Serializable;

/**
 * Operation id and format
 *
 *
 * Date: 5/11/14
 * Time: 12:17 PM
 * @author jsanca
 */
public class PathMappingResultBean implements Serializable {

    private String operationId = null;

    private String format = null;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


} // E:O:F:PathMappingResultBean.
