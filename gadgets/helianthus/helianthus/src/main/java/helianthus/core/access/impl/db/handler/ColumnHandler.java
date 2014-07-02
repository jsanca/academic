package helianthus.core.access.impl.db.handler;

import java.io.Serializable;
import java.sql.PreparedStatement;

/**
 * Defines a column mapping setter
 *
 * Convert a string to the expected object (integer, long, etc) and set it to a statement.
 *
 * Date: 5/6/14
 * Time: 11:11 PM
 * @author jsanca
 */
public interface ColumnHandler<T> extends Serializable {

    /**
     * Map a columnValue to a T
     * @param columnValue Object
     * @param statement PreparedStatement
     * @param indexPosition int
     */
    void set (String columnValue,
              PreparedStatement statement,
              int indexPosition);

    /**
     * Gets the type name associated to this column handler.
     * @return String
     */
    String getTypeName ();
} // E:O:F:ColumnMappingHandler.
