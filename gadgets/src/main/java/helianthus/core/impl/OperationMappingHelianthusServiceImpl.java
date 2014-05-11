package helianthus.core.impl;

import helianthus.core.HelianthusService;
import helianthus.core.access.GenericDataAccess;
import helianthus.core.bean.TableResultBean;
import helianthus.core.util.OperationMappingHelper;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * Date: 5/10/14
 * Time: 11:45 PM
 * @author jsanca
 */
public class OperationMappingHelianthusServiceImpl implements HelianthusService {

    private static Logger logger =
            Logger.getLogger(OperationMappingHelianthusServiceImpl.class.getName());


    private OperationMappingHelper mappingHelper;
    private GenericDataAccess genericDataAccess;

    @Override
    public TableResultBean executeOperation(final String operationId, final Object... params) {

        TableResultBean tableResultBean = null;

        final String query =
            this.mappingHelper.getQuery(operationId);

        logger.log(Level.INFO,
                MessageFormat.format
                        ("The query returned for the operation id {0} is {1}",
                            operationId, query));

        tableResultBean =
                 this.genericDataAccess.executeQuery
                         (query, params);

        return tableResultBean;
    } // executeOperation.


} // E:O:F:OperationMappingHelianthusServiceImpl.
