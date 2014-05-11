package helianthus.core.access.impl.db.handler;


import helianthus.core.InvalidColumnValueException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Column handler for integer.
 *
 * Date: 5/11/14
 * Time: 10:19 AM
 * @author jsanca
 */
public class IntegerColumnHandler implements ColumnHandler<Integer> {

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


        Integer integer = null;

        try {

            integer = Integer.parseInt(columnValue);
            statement.setInt(indexPosition, integer);
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

        return "integer";
    } // getTypeName

} // E:O:F:IntegerColumHandler.
