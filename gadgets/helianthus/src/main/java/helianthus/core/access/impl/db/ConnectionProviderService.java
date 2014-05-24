package helianthus.core.access.impl.db;

import helianthus.core.access.GenericDataAccess;

import java.io.Serializable;
import java.util.Map;

/**
 * Service to get the providerMap.
 *
 * Date: 5/20/14
 * Time: 6:10 PM
 * @author jsanca
 */
public class ConnectionProviderService implements Serializable {

    private Map<String, ConnectionProvider> providerMap;

    /**
     * Get the Connection provider, if data source is null will try to return the default (if there is just one of them, it will be the default too)
     * @param dataSource String
     * @return ConnectionProvider
     */
    public ConnectionProvider getConnectionProvider(final String dataSource) {

        ConnectionProvider connectionProvider = null;

        if (null == dataSource) {

            if (providerMap.size() == 1) {

                connectionProvider =
                        this.providerMap.values().iterator().next();
            } else {

                connectionProvider =
                        this.providerMap.get
                                (GenericDataAccess.DEFAULT_DATA_SOURCE);
            }
        } else {

            connectionProvider =
                    this.providerMap.get
                            (dataSource);
        }

        return connectionProvider;
    } // getConnectionProvider

    public void setProviderMap(Map<String, ConnectionProvider> providerMap) {

        this.providerMap = providerMap;
    }
} // E:O:F:ConnectionProviderService.
