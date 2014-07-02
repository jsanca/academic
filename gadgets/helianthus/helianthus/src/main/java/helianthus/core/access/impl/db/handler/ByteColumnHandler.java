package helianthus.core.access.impl.db.handler;


import helianthus.core.InvalidColumnValueException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Column handler for byte.
 *
 * Date: 5/11/14
 * Time: 10:19 AM
 * @author jsanca
 */
public class ByteColumnHandler implements ColumnHandler<Byte> {

    /**
     * Map a columnValue to a T
     *
     * @param columnValue Object
     * @param statement   PreparedStatement
     */
    @Override
    public void set(final String columnValue,
                    final PreparedStatement statement,
                    final int indexPosition) {


        Byte aByte = null;

        try {

            aByte = Byte.parseByte(columnValue);
            statement.setByte(indexPosition, aByte);
        } catch (SQLException e) {

            throw new InvalidColumnValueException
                    (MessageFormat.format
                            ("The column value {0} for the parameter position {1} is not valid",
                                    columnValue, indexPosition));
        }  catch (NumberFormatException e) {

            throw new InvalidColumnValueException
                    (MessageFormat.format
                            ("The column value {0} for the parameter position {1} is not valid",
                                    columnValue, indexPosition));
        }
    } // set.

    /**
     * Gets the type name associated to this column handler.
     *
     * @return String
     */
    @Override
    public String getTypeName() {

        return "byte";
    } // getTypeName
} // E:O:F:IntegerColumHandler.
