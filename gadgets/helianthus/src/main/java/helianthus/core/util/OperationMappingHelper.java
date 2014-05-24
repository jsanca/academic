package helianthus.core.util;

import helianthus.core.NoMappingException;
import helianthus.core.bean.QueryMappingResult;

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

    private HashMap<String, String> dataSourceMapping =
            new HashMap<String, String>();

    private HashMap<String, String> mapping =
            new HashMap<String, String>();

    private HashMap<String, String[]> typeNameMapping =
            new HashMap<String, String[]>();

    private HashMap<String, String[]> parameterListMapping =
            new HashMap<String, String[]>();

    /**
     * Get the parameter list for this operation
     * @param operationId String
     * @return String array
     */
    public String [] getTypeNameList (final String operationId) {

        if (!this.typeNameMapping.containsKey(operationId)) {

            throw new NoMappingException
                    (MessageFormat.format("Not any associated to the operation id {0}",
                            operationId));
        }

        return this.typeNameMapping
                .get(operationId);
    } // getParameterList.

    /**
     * Get the parameter list for this operation
     * @param operationId String
     * @return String array
     */
    public String [] getParameterList (final String operationId) {

        if (!this.parameterListMapping.containsKey(operationId)) {

            throw new NoMappingException
                    (MessageFormat.format("Not any associated to the operation id {0}",
                            operationId));
        }

        return this.parameterListMapping
                .get(operationId);
    } // getParameterList.

    /**
     * Get the query associated to the operation
     * @param operationId  String
     * @return  QueryMappingResult
     */
    public QueryMappingResult getQuery (final String operationId) {

        final QueryMappingResult queryMappingResult =
                new QueryMappingResult();

        if (!this.mapping.containsKey(operationId)) {

             throw new NoMappingException
                     (MessageFormat.format("Not any associated to the operation id {0}",
                             operationId));
        }

        queryMappingResult.setDataSource
                (this.dataSourceMapping.get(operationId));
        queryMappingResult.setQuery
                (this.mapping.get(operationId));
        queryMappingResult.setTypeNameArray
                (this.typeNameMapping.get(operationId));

        return queryMappingResult;
    } // getQuery.

    public void setMapping(final HashMap<String, String> mapping) {

        this.mapping = mapping;
    }

    public void setTypeNameMapping(final HashMap<String, String[]> typeNameMapping) {

        this.typeNameMapping = typeNameMapping;
    }

    public void setParameterListMapping(final HashMap<String, String[]> parameterListMapping) {

        this.parameterListMapping = parameterListMapping;
    }

    public void setDataSourceMapping(HashMap<String, String> dataSourceMapping) {
        this.dataSourceMapping = dataSourceMapping;
    }
} // E:O:F:OperationMappingHelper.
