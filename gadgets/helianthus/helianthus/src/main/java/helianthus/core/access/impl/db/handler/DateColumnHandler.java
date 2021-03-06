package helianthus.core.access.impl.db.handler;


import helianthus.core.InvalidColumnValueException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Column handler for date.
 *
 * Use the default format for US locale
 *
 * Date: 5/11/14
 * Time: 10:19 AM
 * @author jsanca
 */
public class DateColumnHandler implements ColumnHandler<Integer> {

    private static DateFormat dateFormatter =
            DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
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


        java.util.Date date = null;

        try {

            date = dateFormatter.parse(columnValue);
            statement.setDate(indexPosition,
                    new java.sql.Date(date.getTime()));
        } catch (SQLException e) {

            throw new InvalidColumnValueException
                    (MessageFormat.format
                            ("The column value {0} for the parameter position {1} is not valid",
                                    columnValue, indexPosition));
        } catch (ParseException e) {

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
