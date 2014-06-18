package helianthus.core.marshall.tableresult;

import helianthus.core.bean.TableResultBean;
import helianthus.core.marshall.MarshallFormatter;
import helianthus.core.marshall.html.HTMLTableFormatter;
import helianthus.core.marshall.html.config.HTMLConfig;

import java.io.OutputStream;
import java.util.HashMap;

/**
 * Table result html formatter
 * Date: 5/11/14
 * Time: 1:09 PM
 * @author jsanca
 */
public class TableResultHTMLMarshallFormatter implements MarshallFormatter {

    private static HTMLTableFormatter htmlTableFormatter = null;

    static {

        final HashMap<HTMLConfig, String> config =
                new HashMap<HTMLConfig, String>();

        config.put(HTMLConfig.TABLE_HEADER, "columnNames");
        config.put(HTMLConfig.TBODY, "rowResultBeans");

        htmlTableFormatter =
                new HTMLTableFormatter(config);
    }

    /**
     * Process the response
     *
     * @param outputStream    OutputStream
     * @param tableResultBean TableResultBean
     */
    @Override
    public void process(
            final OutputStream outputStream,
            final TableResultBean tableResultBean) {


        if (null != htmlTableFormatter) {

            htmlTableFormatter.formatter
                    (outputStream, tableResultBean);
        }
    } // process.

} // E:O:F:JsonMarshallFormatter.
