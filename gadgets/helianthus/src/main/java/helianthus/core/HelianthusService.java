package helianthus.core;

import helianthus.core.bean.TableResultBean;

import java.io.Serializable;

/**
 *  Exposes the basic Helianthus functionality
 *
 * Date: 5/10/14
 * Time: 11:35 PM
 * @author jsanca
 */
public interface HelianthusService extends Serializable {

    public TableResultBean executeOperation
            (String operationId, Object... params);

} // E:O:F:HelianthusService.
