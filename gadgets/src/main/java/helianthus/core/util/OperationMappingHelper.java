package helianthus.core.util;

import helianthus.core.NoMappingException;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;

/**
 * Mapping between operation and queries
 *
 * Date: 5/10/14
 * Time: 11:48 PM
 * @author jsanca
 */
public class OperationMappingHelper implements Serializable {

    private HashMap<String, String> mapping =
            new HashMap<String, String>();

    /**
     * Get the query associated to the operation
     * @param operationId  String
     * @return  String
     */
    public String getQuery (final String operationId) {

        if (!this.mapping.containsKey(operationId)) {

             throw new NoMappingException
                     (MessageFormat.format("Not any associated to the operation id {0}",
                             operationId));
        }

        return mapping.get(operationId);
    } // getQuery.
} // E:O:F:OperationMappingHelper.
