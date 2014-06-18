package helianthus.core.util;

import helianthus.core.bean.PathMappingResultBean;

import java.io.Serializable;

/**
 *
 *
 * Date: 5/11/14
 * Time: 12:15 PM
 * @author jsanca
 */
public class PathHandler implements Serializable {

    /**
     * Parse a path getting the operation id and format.
     * @param path String
     * @return  PathMappingResultBean
     */
    public PathMappingResultBean parsePath (final String path) {

        final PathMappingResultBean resultBean =
                new PathMappingResultBean();

        int dotIndexOf =
                path.indexOf(".");

        String operationPath = null;
        String format = null;

        if (-1 != dotIndexOf) {


            operationPath = path.substring(0, dotIndexOf);
            format = path.substring(dotIndexOf + 1);
        } else {

            operationPath = path;
        }

        resultBean.setFormat(format);
        resultBean.setOperationId(operationPath);

        return resultBean;
    } // E:O:F:parsePath.
} // E:O:F:PathHandler.
