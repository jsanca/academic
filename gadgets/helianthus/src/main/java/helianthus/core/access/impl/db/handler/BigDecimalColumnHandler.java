package helianthus.core.access.impl.db.handler;


import helianthus.core.InvalidColumnValueException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Column handler for big integer.
 *
 * Date: 5/11/14
 * Time: 10:19 AM
 * @author jsanca
 */
public class BigDecimalColumnHandler implements ColumnHandler<BigDecimal> {

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


        BigDecimal bigDecimal = null;

        try {

            bigDecimal = new BigDecimal(columnValue);
            statement.setBigDecimal(indexPosition, bigDecimal);
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

        return "bigdecimal";
    } // getTypeName
} // E:O:F:IntegerColumHandler.
