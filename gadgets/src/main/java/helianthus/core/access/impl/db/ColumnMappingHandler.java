package helianthus.core.access.impl.db;

import java.io.Serializable;

/**
 * Defines a column mapping handler
 *
 * Date: 5/6/14
 * Time: 11:11 PM
 * @author jsanca
 */
public interface ColumnMappingHandler<T> extends Serializable {

    /**
     * Map a columnValue to a T
     * @param columnValue Object
     * @return T
     */
    T map (Object columnValue);
} // E:O:F:ColumnMappingHandler.
