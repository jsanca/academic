package helianthus.core.marshall;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Marshall Format Factory
 *
 * Formats allowed: json, xml, html.
 *
 * Date: 5/11/14
 * Time: 12:59 PM
 * @author jsanca
 */
public class MarshallFormatFactory implements Serializable {

    private  HashMap<String, MarshallFormatter> marshallFormatterHashMap =
            new HashMap<String, MarshallFormatter>();

    /*static {

        marshallFormatterHashMap.put("json",
                new JSONMarshallFormatter()
                );

        marshallFormatterHashMap.put("xml",
                new JSONMarshallFormatter()
        );


        marshallFormatterHashMap.put("html",
                new JSONMarshallFormatter()
        );
    } */

    /**
     * Get the marshall formatter for the format argument.
     * @param format  String
     * @return  MarshallFormatter
     */
    public MarshallFormatter getMarshallFormatter(final String format) {

        MarshallFormatter marshallFormatter = null;

        if (marshallFormatterHashMap.containsKey(format)) {

            marshallFormatter =
                marshallFormatterHashMap.get(format);
        } else {

            // the default is html.
            marshallFormatter =
                marshallFormatterHashMap.get("html");
        }

        return marshallFormatter;
    } // getMarshallFormatter.

    public void setMarshallFormatterHashMap
            (final HashMap<String, MarshallFormatter> marshallFormatterHashMap) {

        this.marshallFormatterHashMap = marshallFormatterHashMap;
    }
} // E:O:F:MarshallFormatFactory.
