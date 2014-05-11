package helianthus.core.access.impl.db.handler;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Factory
 * Date: 5/11/14
 * Time: 11:21 AM
 * @author jsanca
 */
public class ColumnHandlerFactory implements Serializable {

    public static final ColumnHandlerFactory INSTANCE =
            new ColumnHandlerFactory();

    private static HashMap<String, ColumnHandler<?>> columnHandlerMappingMap =
            new HashMap<String, ColumnHandler<?>>();

    private final static StringColumnHandler DEFAULT_COLUMN_HANDLER =
            new StringColumnHandler();

    static {

        final ColumnHandler<?> [] columnHandlers =
                new ColumnHandler<?>[] {
                        new BigDecimalColumnHandler(),
                        new BooleanColumnHandler(),
                        new ByteColumnHandler(),
                        new DateColumnHandler(),
                        new DoubleColumnHandler(),
                        new FloatColumnHandler(),
                        new IntegerColumnHandler(),
                        new LongColumnHandler(),
                        DEFAULT_COLUMN_HANDLER
                };

        for (ColumnHandler<?> columnHandler : columnHandlers) {

            columnHandlerMappingMap.put
                (columnHandler.getTypeName(), columnHandler);
        }
    }

    /**
     * Get the column handler for the type name passed
     * @param typeName String
     * @return ColumnHandler
     */
    public  ColumnHandler<?> getColumnHandler (final String typeName) {

        ColumnHandler<?> columnHandler = null;

        if (columnHandlerMappingMap.containsKey(typeName)) {

            columnHandler =
                    columnHandlerMappingMap.get(typeName);
        } else {

            columnHandler = DEFAULT_COLUMN_HANDLER;
        }

        return columnHandler;
    } // getColumnHandler.
} // E:O:F:ColumnHandlerFactory.
