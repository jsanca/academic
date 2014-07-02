package helianthus.core.marshall.csv.config;

import java.io.Serializable;

/**
 * Configuration for the CSV formatter
 * Date: 7/1/14
 * Time: 10:06 PM
 * @author jsanca
 */
public enum CSVConfig implements Serializable {

    /**
     * Defines a collection that will be the header.
     */
    TABLE_HEADER,

    /**
     * Defines a collection/collection that will be the table data (collections of rows)
     */
    TABLE_DATA


} // E:O:F:CSVConfig.
