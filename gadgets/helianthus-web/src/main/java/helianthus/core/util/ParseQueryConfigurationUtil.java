package helianthus.core.util;

import helianthus.core.bean.QueryConfigBean;
import helianthus.core.bean.QueryParameterBean;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Parse an input stream with the query configuration
 *
 * Date: 5/20/14
 * Time: 6:33 PM
 * @author jsanca
 */
public class ParseQueryConfigurationUtil implements Serializable {

    private static Digester digester = new Digester();

    private static final String[] EMPTY_ARRAY = new String[] {};

    static {

        digester.addObjectCreate("queries", ArrayList.class);
        digester.addObjectCreate("queries/query-config", QueryConfigBean.class);
        digester.addSetProperties("queries/query-config");

        digester.addObjectCreate("queries/query-config/parameters", ArrayList.class);
        digester.addObjectCreate("queries/query-config/parameters/parameter", QueryParameterBean.class);
        digester.addSetProperties("queries/query-config/parameters/parameter");
        digester.addSetNext("queries/query-config/parameters/parameter", "add" ); // add the query parameter
        digester.addSetNext("queries/query-config/parameters", "setParameters");



        digester.addCallMethod("queries/query-config/query", "setQuery", 1);
        digester.addCallParam("queries/query-config/query", 0);

        digester.addSetNext("queries/query-config", "add" ); // add the query config
    }

    public OperationMappingHelper parse (final InputStream inputStream) throws IOException, SAXException {

        final OperationMappingHelper operationMappingHelper =
                new OperationMappingHelper();

        this.parse(inputStream, operationMappingHelper);

        return operationMappingHelper;
    } // parse



    private void parse(
            final InputStream inputStream,
            final OperationMappingHelper operationMappingHelper) throws IOException, SAXException {

        final HashMap<String, String> mapping =
                new HashMap<String, String>();

        final HashMap<String, String[]> typeNameMapping =
                new HashMap<String, String[]>();

        final HashMap<String, String[]> parameterListMapping =
                new HashMap<String, String[]>();

        final HashMap<String, String> dataSourceMapping =
                new HashMap<String, String>();

        final ArrayList<QueryConfigBean> queryConfigBeans =
                digester.parse(inputStream);

        for (QueryConfigBean queryConfigBean : queryConfigBeans) {

            mapping.put(queryConfigBean.getName(),
                   queryConfigBean.getQuery());

            if (null != queryConfigBean.getParameters()) {

                typeNameMapping.put(queryConfigBean.getName(),
                    this.getTypeNames(queryConfigBean.getParameters()));
            } else {

                typeNameMapping.put(queryConfigBean.getName(),
                        EMPTY_ARRAY);
            }

            if (null != queryConfigBean.getParameters()) {

                parameterListMapping.put(queryConfigBean.getName(),
                    this.getParameterList(queryConfigBean.getParameters()));
            } else {

                parameterListMapping.put(queryConfigBean.getName(),
                        EMPTY_ARRAY);
            }

            if (null != queryConfigBean.getDatasource()) {

                dataSourceMapping.put(queryConfigBean.getName(),
                    queryConfigBean.getDatasource()
                    );
            }
        }

        operationMappingHelper.setMapping(mapping);
        operationMappingHelper.setTypeNameMapping(typeNameMapping);
        operationMappingHelper.setParameterListMapping(parameterListMapping);
    } // parse.

    private String[] getParameterList(final ArrayList<QueryParameterBean> parameters) {

        final String [] parameterList =
                new String[parameters.size()];
        int i = 0;

        for(QueryParameterBean queryParameterBean : parameters) {

            parameterList[i++] = queryParameterBean.getName();
        }

        return parameterList;
    }

    private String[] getTypeNames(final ArrayList<QueryParameterBean> parameters) {

        final String [] typeNames =
                new String[parameters.size()];
        int i = 0;

        for(QueryParameterBean queryParameterBean : parameters) {

            typeNames[i++] = queryParameterBean.getType();
        }

        return typeNames;
    }

} // E:O:F:ParseQueryConfigurationService.
